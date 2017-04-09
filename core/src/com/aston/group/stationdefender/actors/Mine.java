package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;

/**
 * Mine Alien with low range, low rate of fire and no speed (stays in one place) but has a high damage
 *
 * @author IngramJ
 */
public class Mine extends Alien {
    private long lastTime;

    /**
     * Create a new Mine with default X and Y co-ordinates of '0'
     */
    public Mine() {
        super("Mine", 0, 60, 4.0, Constants.UNIT_HEALTH, 4, 0.9, 60, 50, 11);
    }

    @Override
    public void act(float delta) {
        if (!checkZeroHealth()) {
            if (!isAdjacent) {
                if (unitCallback != null && System.currentTimeMillis() - lastTime >= (10000 / rateOfFire)) {
                    unitCallback.onFire(x - 40, y + 35, -30, getDamage());
                    lastTime = System.currentTimeMillis();
                }
            }
        } else {
            destroy();
        }
    }
}