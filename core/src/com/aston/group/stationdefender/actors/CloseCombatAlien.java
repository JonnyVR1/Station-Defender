package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;

/**
 * Fast Alien with slow, but high damage.
 *
 * @author IngramJ
 */
public class CloseCombatAlien extends Alien {

    /**
     * Create a new CloseCombatAlien with default X and Y co-ordinates of '0'
     */
    public CloseCombatAlien() {
        super("Close Combat Alien", -100, 60.0, 2, Constants.UNIT_HEALTH, 1, 7.0, 20, 20, 22);
    }
}