package com.aston.group.stationdefender.actors;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Boss Alien displayed at the end of the level.  Low speed but high damage.
 *
 * @author Jonathon Fitch
 */
public class BossAlien extends Alien {

    /**
     * Create a new BossAlien with default X and Y co-ordinates of '0'
     */
    public BossAlien() {
        super("Alien", -60, 200, 5, 800, 5.0, 8, 300, 225, ThreadLocalRandom.current().nextInt(15, 19));
    }
}