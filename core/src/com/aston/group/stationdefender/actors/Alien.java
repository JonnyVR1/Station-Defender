package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * Superclass for different Alien types.
 *
 * @author IngramJ
 * @version 01/11/2016
 */
public class Alien extends Unit {
    private boolean overloaded;

    /**
     * Construct a new default Alien
     */
    public Alien() {
        this("Alien", -120, Constants.DEFAULT_DAMAGE, 5, Constants.UNIT_HEALTH, 5.0, 12, 100, 38, TextureManager.ALIEN);
    }

    /**
     * Construct a new Alien with given name, speed, damage, rateOfFile, health, range, chance to hit, width and height and texture parameters
     *
     * @param name        The name of the Alien
     * @param speed       The speed of the Alien
     * @param damage      The damage the Alien inflicts
     * @param rateOfFire  The rate of fire of the Alien
     * @param health      The health of the Alien
     * @param range       The range of the Alien
     * @param chanceToHit The chance of the Weapon to score a hit
     * @param width       The width of the Alien
     * @param height      The height of the Alien
     * @param texture     The texture graphic of the Alien
     */
    public Alien(String name, double speed, double damage, double rateOfFire, double health, double range, double chanceToHit, int width, int height, TextureManager texture) {
        super(name, speed, damage, rateOfFire, health, range, chanceToHit, width, height, true, texture);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (!isAdjacent)
            batch.setColor(1f, 1f, 1f, 1f);
        else
            batch.setColor(.5f, .5f, .5f, 1f);
        batch.draw(texture, x, y, width, height);
        batch.end();
        checkInput();
        act(delta);
        indicatorManager.render(delta, x, y);
    }

    private void act(float delta) {
        if (checkIsNotZeroHealth()) {
            switch (name) {
                case "Kamikaze Alien":
                    if (isAdjacent) {
                        adjacentActor.takeDamage(fire());
                        destroy();
                    } else {
                        move(delta);
                    }
                    break;
                case "Mine":
                    if (!isAdjacent) {
                        unitFireHelper(-10, -30);
                    }
                    break;
                case "Rapid Fire Alien":
                    if (overloaded)
                        overloaded = false;
                    else {
                        if (isAdjacent) {
                            overloaded = rapidFireHelper();
                        } else {
                            move(delta);
                        }
                    }
                    break;
                default:
                    if (isAdjacent) {
                        adjacentActor.takeDamage(fire());
                    } else {
                        move(delta);
                    }
                    break;
            }
        } else {
            destroy();
        }
    }

    /**
     * Moves the Alien from the left side of the lane to the right
     *
     * @param delta The time in seconds since the last move
     */
    private void move(float delta) {
        if (!isAdjacent)
            x += (speed * delta);
        else if (getAdjacentActor() != null && !((Unit) getAdjacentActor()).isFacingLeft())
            getAdjacentActor().takeDamage(getDamage());
    }
}