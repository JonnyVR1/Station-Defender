package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.TextureManager;

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
        super("Close Combat Weapon", 25, 50.0, 2.0, Constants.UNIT_HEALTH, 4.0, 7.0, 0, 0, 60, 60, 2.0, 15, 25);
        setTexture(TextureManager.INSTANCE.loadTexture(25));
    }
}