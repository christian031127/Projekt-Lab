package org.example;

import java.awt.*;
import java.awt.geom.QuadCurve2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class Map extends JPanel{

    private static Logger logger = Logger.getLogger("TesztLogger");

    public Player currentPlayer;
    private static HashMap<String, GraphicsTekton> Tektons = new HashMap<String, GraphicsTekton>();
    private static HashMap<String, GraphicsPlayer> Players = new HashMap<String, GraphicsPlayer>();
    static String Name;

    public static int currentTurn = 0;

    public void loadMap() throws Exception {
        // read file, load map
        Scanner scanner = null;
        try {
            File file = new File("map.txt");
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Cannot find Map");
        }
        String[] command = null;
        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            //Kommente figyelmen kívül hagyása
            if (line.contains("#") || line.contains("//")) {
                continue;
            }
            command = line.split(" ");

            switch (command[0]) {
                case "add": {
                    Tekton uj = new Tekton();
                    //Van e elég argumentum?
                    if (command.length < 3) {
                        logger.log(Level.SEVERE, "Not enough arguments for 'add' command! (minimum 2 argument)");
                        throw new Exception("Not enough arguments for 'add' command! (minimum 2 argument)");
                    }
                    if (command.length == 5) {
                        switch (command[1]) {
                            case "Tekton":
                                uj.setStrategy(new MultipleYarnTekton());
                                Name = command[2];
                                GraphicsTekton t1=new GraphicsTekton(Integer.parseInt(command[3]),Integer.parseInt(command[4]),uj);
                                Tektons.put(Name, t1);
                                break;

                            default:
                                logger.log(Level.SEVERE, "Cannot find gameObject type {0}!", command[1]);
                                throw new AssertionError();
                        }
                    }
                    if (command.length > 5) {
                        switch (command[1]) {
                            case "Tekton":
                                switch (command[3]) {
                                    case "AbsorbTekton":
                                        uj.setStrategy(new AbsorbTekton());
                                        Name = command[2];
                                        break;
                                    case "SingleYarnTekton":
                                        uj.setStrategy(new SingleYarnTekton());
                                        Name = command[2];
                                        break;
                                    case "NonShroomTekton":
                                        uj.setStrategy(new NonShroomTekton());
                                        Name = command[2];
                                        break;
                                    case "KeepAliveTekton":
                                        uj.setStrategy(new KeepAliveTekton());
                                        Name = command[2];
                                        break;
                                    case "MultipleYarnTekton":
                                        uj.setStrategy(new MultipleYarnTekton());
                                        Name = command[2];
                                        break;
                                    default:

                                        throw new AssertionError();
                                }
                                GraphicsTekton t=new GraphicsTekton(Integer.parseInt(command[4]),Integer.parseInt(command[5]),uj);
                                Tektons.put(Name, t);
                                break;

                            case "Player":
                                if (command.length != 8) {
                                    throw new Exception("add Player takes 3 argument!");
                                }
                                Player p1 = new Player();
                                p1.setIsInsect(command[4].equals("Insect"));
                                p1.setCurrentTekton(Tektons.get(command[3]).getTekton());
                                if(command[4].equals("Shroomer")){
                                    Shroom s=new Shroom();
                                    s.setPlayerId(Integer.parseInt(command[7]));
                                    p1.getCurrentTekton().getFirst().addShroom(s);
                                    s.setTekton(p1.getCurrentTekton().getFirst());
                                }
                                Name = command[2];
                                p1.setPlayer_id(Integer.parseInt(command[7]));
                                GraphicsPlayer p=new GraphicsPlayer(Integer.parseInt(command[5]),Integer.parseInt(command[6]),p1);
                                p.setDrawId(Integer.parseInt(command[7]));
                                Players.put(Name, p);
                                break;
                            default:
                                logger.log(Level.SEVERE, "Cannot find gameObject type {0}!", command[1]);
                                throw new AssertionError();
                        }
                    }


                    logger.log(Level.INFO, "Adding new gameObject {0}!", command[2]);
                    break;
                }

                case "neighbour": {
                    if (command.length != 3) {
                        logger.log(Level.SEVERE, "Command neighbour takes 2 arguments!");
                        throw new Exception("Command neighbour takes 2 arguments!");
                    }
                    Tektons.get(command[1]).getTekton().addNeighbour(Tektons.get(command[2]).getTekton());

                    Tektons.get(command[2]).getTekton().addNeighbour(Tektons.get(command[1]).getTekton());

                    logger.log(Level.INFO, "Tekton " + command[1] + " Tekton " + command[2] + " are now neighbours!");
                    break;
                }

                default: {
                    logger.log(Level.WARNING, "Command {0} not found!", command[0]);
                    break;
                }
            }
        }
        currentPlayer=Players.values().iterator().next().getPlayer();

    }
    public void splitTekton(Tekton tekton){
        logger.log(Level.FINE,"Map.splitTekton() called");
        List<Tekton> neighbours = tekton.getNeighbours();
        tekton.split();

        for (GraphicsTekton t:Tektons.values()){
            if(t.getTekton().equals(tekton)){
                Tektons.values().remove(t);
                break;
            }
        }

        GraphicsTekton t3 = new GraphicsTekton(0,0,new Tekton());
        GraphicsTekton t4 = new GraphicsTekton(0,0,new Tekton());
        t3.getTekton().addNeighbour(t4.getTekton());
        t4.getTekton().addNeighbour(t3.getTekton());

        for(Tekton tf : neighbours){
            t3.getTekton().addNeighbour(tf);
            t4.getTekton().addNeighbour(tf);
        }
        Tektons.put("T"+Tektons.size()+1,t3);
        Tektons.put("T"+Tektons.size()+2,t4);


    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (GraphicsTekton t:Tektons.values()){
            t.draw((Graphics2D) g);
        }
        isItSplitted();
        Iterator<GraphicsPlayer> it = Players.values().iterator();

        while (it.hasNext()) {
            GraphicsPlayer np=it.next();
            if(np.getPlayer().isDead()){
                it.remove();
            }
            else{

                for(GraphicsTekton t:Tektons.values()){
                    if(t.getTekton()==np.getPlayer().getCurrentTekton().getFirst() && np.getPlayer().getIsInsect()){
                        if(np.getPlayer().getEffects()[3]==2){
                            np.x=t.x+30;
                        }
                        else{
                            np.x=t.x;
                        }
                        np.y=t.y;
                    }
                }

                np.draw((Graphics2D) g);
            }

        }

        Set<Yarn> drawnYarns = new HashSet<>();

        for (GraphicsTekton t : Tektons.values()) {
            for (Yarn yarn : t.getTekton().getYarns()) {
                if (!drawnYarns.contains(yarn)) {
                    GraphicsTekton t3=null;
                    for (GraphicsTekton t2 : Tektons.values()) {
                        if(t2.getTekton()==yarn.getTekton2() && yarn.getTekton2()!=t.getTekton() || t2.getTekton()==yarn.getTekton1() && yarn.getTekton1()!=t.getTekton()){
                            t3=t2;
                        }
                    }
                    if(t3!=null){
                        int x1 = t.x + 120 / 2;
                        int y1 = t.y + 120 / 2;
                        int x2 = t3.x + 120 / 2;
                        int y2 = t3.y + 120 / 2;
                        Graphics2D gg=(Graphics2D)g;
                        Stroke originalStroke = gg.getStroke();

                        gg.setStroke(new BasicStroke(3));
                        if(yarn.getShroomPlayerId()==1){
                            gg.setColor(Color.RED);
                        }
                        if(yarn.getShroomPlayerId()==2){
                            gg.setColor(new Color(139, 69, 19));
                        }
                        QuadCurve2D curve = new QuadCurve2D.Float();

                        int ctrlX = (x1 + x2) / 2 + (int)(Math.random() * 20 - 10); // ±10 pixeles eltolás
                        int ctrlY = (y1 + y2) / 2 + (int)(Math.random() * 20 - 10);

                        curve.setCurve(x1, y1, ctrlX, ctrlY, x2, y2);
                        gg.draw(curve);
                        gg.setStroke(originalStroke);
                        drawnYarns.add(yarn);
                    }


                }
            }
        }
    }

    public void nextPlayer(){

        List<GraphicsPlayer> playerList = new ArrayList<>(Players.values());
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getPlayer().equals(currentPlayer)) {
                int nextIndex = (i + 1) % playerList.size();
                currentPlayer = playerList.get(nextIndex).getPlayer();
                currentPlayer.steps_in_round_reset();
                if(currentTurn % playerList.size() == 0) {
                    for (GraphicsTekton tekton : Tektons.values()) {
                        Tekton t = tekton.getTekton();
                        if (t.getShroom() != null)
                            t.getShroom().age();
                        if(t.getShroom().isDead()){
                            for (GraphicsPlayer p : Players.values()) {
                                if(p.getPlayer().getPlayer_id() == t.getShroom().getPlayerId()){
                                    p.getPlayer().handleShroomDeath(t);
                                    
                                }
                            }
                        }
                    }
                }
                currentTurn++;
                break;
            }
        }
    }
    public GraphicsTekton getTektonbyClick(int x , int y){
        for (GraphicsTekton t:Tektons.values()){
            if(x>t.x && x<t.x+120 && y>t.y && y<t.y+120){

                return t;
            }
        }
        return null;
    }
    public GraphicsPlayer playerOnTektonBelowEffect(GraphicsTekton t){
        for(GraphicsPlayer p:Players.values()){
            if(p.getPlayer().getCurrentTekton().getFirst()==t.getTekton() && p.getPlayer().getEffects()[1]==1 && p.getPlayer().getIsInsect()){
                return p;
            }
        }
        return null;
    }

    public int getCurrentTurn() {
        return (currentTurn / 4) + 1;
    }
    public void isItSplitted(){
        HashMap<String,GraphicsPlayer> players=new HashMap<>();
        for(GraphicsPlayer p : Players.values()){
            if(p.getPlayer().getEffects()[3]==1){
                p.getPlayer().setEffects(3,2);
                Player pn=new Player();
                pn.setPlayer_id(p.getPlayer().getPlayer_id());

                pn.setCurrentTekton(p.getPlayer().getCurrentTekton().getFirst());
                pn.setIsInsect(true);
                GraphicsPlayer gp=new GraphicsPlayer(p.x,p.y,pn);
                gp.setDrawId(p.getDrawId()*10);
                players.put("In"+Players.size()+1,gp);
            }
        }
        Players.putAll(players);
    }
}
