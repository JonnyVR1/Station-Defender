package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * Alien with slow rate of fire and speed but a high damage.
 *
 * @author IngramJ
 */
public class KamikazeAlien extends Alien {

    /**
     * Create a new KamikazeAlien with default X and Y co-ordinates of '0'
     */
    public KamikazeAlien() {
        super("Kamikaze Alien", -100, 100, 1, Constants.UNIT_HEALTH, 1.0, 0.9, 0, 0, 100, 38);
        setTexture(TextureManager.INSTANCE.loadTexture(14));
    }

    @Override
    public void act(float delta) {
        if (!checkZeroHealth()) {
            if (isAdjacent && !(getAdjacentActor() instanceof Mine)) {
                adjacentActor.takeDamage(fire());
                destroy();
            } else {
                move(delta);
            }
        } else {
            destroy();
        }
    }
}