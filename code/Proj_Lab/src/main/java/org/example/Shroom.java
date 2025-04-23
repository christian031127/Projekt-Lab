package org.example;

import java.util.Random;

public class Shroom {

    private int playerId;

    private Tekton tekton;
    private int age = 0;

    private int lastEject = 0;
    private int ejectCount = 0;

    private static final int OLD_LIMIT = 10;
    private static final int MAX_EJECTS = 5; // max ennyiszer szórhat
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

    public Shroom () {
        this.tekton = null;
        this.playerId = -1;
    }
    
    public Shroom(Tekton tekton, int playerId) {
        this.tekton = tekton;
        this.playerId = playerId;
    }

    public boolean isOld() {
        return age >= OLD_LIMIT;
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isSporeReady() {
        int currentTurn = Map.currentTurn; //Lekérdezni a jelenlegi kör számát

        boolean cooldownElapsed = (currentTurn - lastEject) >= EJECT_COOLDOWN;
        boolean hasRemainingEjects = ejectCount < MAX_EJECTS;
        boolean isAlive = !dead;

        return cooldownElapsed && hasRemainingEjects && isAlive;
    }

    public void ejectSpore(Tekton target) {

        //Lekérdezni a jelenlegi kör számát
        int currentTurn = Map.currentTurn;
        boolean advenced = false;

        if (tekton.getNeighbours().contains(target)) {
            advenced = true;
        }

        if (isSporeReady()) {

            if (isOld() && advenced) {

                SporeType selectedSpore = getRandomSpore();
                target.addSpore(createSpore(selectedSpore));
                lastEject = currentTurn;
                ejectCount++;
            }

            if (!advenced) {

                SporeType selectedSpore = getRandomSpore();
                target.addSpore(createSpore(selectedSpore));
                lastEject = currentTurn;
                ejectCount++;
            }
        } else {
            return;
        }

        if (ejectCount >= MAX_EJECTS) {
            die();
        }
    }

    private void die() {
        this.dead = true;
        if (tekton != null) {
            tekton.removeShroom();
        }
    }

    public void age() {
        if (dead) {
            return;
        }
        this.age++;
        if (shouldDieFromOldAge()) {
            die();
        }
    }

    private boolean shouldDieFromOldAge() {
        if (age < OLD_LIMIT) {
            return false;
        }

        int chance = 100 - (100 / Math.max(1, age / 2));
        return rand.nextInt(100) < chance;
    }

    // Véletlen spóra típust generál
    private SporeType getRandomSpore() {
        SporeType[] values = SporeType.values();
        return values[rand.nextInt(values.length)];
    }

    private Spore createSpore(SporeType sporeType) {
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
        return playerId;
    }

    public void setPlayerId(int id) {
        this.playerId = id;
    }

    public Tekton getTekton() {
        return tekton;
    }

    public void setTekton(Tekton tekton) {
        this.tekton = tekton;
    }

    public int getAge() {
        return age;
    }

    public int getLastEject() {
        return lastEject;
    }
}
