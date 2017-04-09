package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;

/**
 * Fast Alien with slow speed, but high damage.
 *
 * @author IngramJ
 */
public class CloseCombatWeapon extends Weapon {

    /**
     * Create a new CloseCombatWeapon default X and Y co-ordinates of '0'
     */
    public CloseCombatWeapon() {
        super("Close Combat Weapon", 25, 50.0, 2.0, Constants.UNIT_HEALTH, 4.0, 7.0, 2.0, 15, 25, 25);
    }
}