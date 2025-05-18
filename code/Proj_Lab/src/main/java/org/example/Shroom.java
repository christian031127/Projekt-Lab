package org.example;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Shroom {

    private static Logger logger = Logger.getLogger("TesztLogger");

    private int playerId;
    private Tekton tekton;
    private int age = 0;

    private int lastEject = 0;
    private int ejectCount = 0;

    private static final int OLD_LIMIT = 10;
    private static final int MAX_EJECTS = 8; // max ennyiszer szórhat
    private static final int EJECT_COOLDOWN = 2; // ennyi körönként szórhat újra

    private boolean dead = false; // gomba halott-e

    private static final Random rand = new Random();

    public enum SporeType {
        ACCELERATION,
        SLOWING,
        NUMBING,
        WEAKENING,
        SPLITTING;
    }

    public Shroom() {
        this.tekton = null;
        this.playerId = -1;
    }

    public Shroom(Tekton tekton, int playerId) {
        logger.log(Level.INFO, "Shroom constructor called");
        this.tekton = tekton;
        this.playerId = playerId;
    }

    public boolean isOld() {
        logger.log(Level.INFO, "Shroom.isOld() called");
        return age >= OLD_LIMIT;
    }

    public boolean isDead() {
        logger.log(Level.INFO, "Shroom.isDead() called");
        return dead;
    }

    public boolean isSporeReady() {
        logger.log(Level.INFO, "Shroom.isSporeReady() called");
        int currentTurn = Map.currentTurn; //Lekérdezni a jelenlegi kör számát

        boolean cooldownElapsed = (currentTurn - lastEject) >= EJECT_COOLDOWN;
        boolean hasRemainingEjects = ejectCount < MAX_EJECTS;
        boolean isAlive = !dead;

        return cooldownElapsed && hasRemainingEjects && isAlive;
    }

    public Spore ejectSpore(Tekton target) {
        logger.log(Level.INFO, "Shroom.ejectSpore() called");
        //Lekérdezni a jelenlegi kör számát
        int currentTurn = Map.currentTurn;

        boolean basic = tekton.getNeighbours().contains(target);
        
        boolean advanced = tekton.getNeighbours().stream().anyMatch(neighbour -> neighbour.getNeighbours().contains(target));
        
        logger.log(Level.INFO, Boolean.toString(advanced));
        if (!isSporeReady()) {
             return null;
         }

        if (isOld() && advanced) {

            SporeType selectedSpore = getRandomSpore();
            Spore spore = createSpore(selectedSpore);
            target.addSpore(spore);
            lastEject = currentTurn;
            ejectCount++;

            if (ejectCount >= MAX_EJECTS) {
                die();
            }

            logger.log(Level.INFO, "ejectspore sikeres!");
            return spore;
        } else if (basic) {

            SporeType selectedSpore = getRandomSpore();
            Spore spore = createSpore(selectedSpore);
            target.addSpore(spore);
            lastEject = currentTurn;
            ejectCount++;

            if (ejectCount >= MAX_EJECTS) {
                die();
            }

            logger.log(Level.INFO, "ejectspore sikeres!");
            return spore;
        }

        
        return null;
    }

    private void die() {
        logger.log(Level.INFO, "Shroom.die() called");
        this.dead = true;
        if (tekton != null) {
            tekton.removeShroom();
            logger.log(Level.INFO, "Shroom died and removed from tekton sikeres!");
        }
    }

    public void age() {
        logger.log(Level.INFO, "Shroom.age() called");
        if (dead) {
            return;
        }
        this.age++;
        if (shouldDieFromOldAge()) {
            die();
        }
    }

    private boolean shouldDieFromOldAge() {
        logger.log(Level.INFO, "Shroom.shouldDieFromOldAge() called");
        if (age < 12) {
            return false;
        }

        int chance = 100 - (100 / Math.max(1, age / 2));
        return rand.nextInt(100) < chance;
    }

    // Véletlen spóra típust generál
    private SporeType getRandomSpore() {
        logger.log(Level.INFO, "Shroom.getRandomSpore() called");
        SporeType[] values = SporeType.values();
        return values[rand.nextInt(values.length)];
    }

    private Spore createSpore(SporeType sporeType) {
        logger.log(Level.INFO, "Shroom.createSpore() called");
        return switch (sporeType) {
            case ACCELERATION ->
                new AccelerationSpore();
            case SLOWING ->
                new SlowingSpore();
            case NUMBING ->
                new NumbingSpore();
            case WEAKENING ->
                new WeakeningSpore();
            case SPLITTING ->
                new SplitterSpore();
        };
    }

    // GETTERS, SETTERS
    public int getPlayerId() {
        logger.log(Level.INFO, "Shroom.getPlayerId() called");
        return playerId;
    }

    public void setPlayerId(int id) {
        logger.log(Level.INFO, "Shroom.setPlayerId() called");
        this.playerId = id;
    }

    public Tekton getTekton() {
        logger.log(Level.INFO, "Shroom.getTekton() called");
        return tekton;
    }

    public void setTekton(Tekton tekton) {
        logger.log(Level.FINE, "Shroom.setTekton() called");
        this.tekton = tekton;
    }

    public int getAge() {
        logger.log(Level.INFO, "Shroom.getAge() called");
        return age;
    }

    public int getLastEject() {
        logger.log(Level.INFO, "Shroom.getLastEject() called");
        return lastEject;
    }
}
