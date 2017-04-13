package com.aston.group.stationdefender.actors;

import com.aston.group.stationdefender.config.Constants;

/**
 * Weapon is a class that represents a weapon object
 * that Humans (Unit) can arm themselves with and use to destroy Aliens
 *
 * @author Jamie Ingram
 * @version 01/11/2016
 */
public class Weapon extends Unit {
    private final double buildTime;
    private final int cost;
    private boolean built = false;
    private int costToUpgrade;
    private double remainingBuildTime;
    private long startTime;
    private boolean overloaded = false;

    /**
     * Construct a new Weapon with default X and Y co-ordinates of '0'
     */
    public Weapon() {
        this("Weapon", 50, Constants.DEFAULT_DAMAGE, 10.0, Constants.WEAPON_HEALTH, 12, 5.0, 1.5, 10, 10, 8);
    }

    /**
     * Construct a new Weapon with given name, speed, damage, rateOfFile, health, range, x co-ordinate, y co-ordinate,
     * width, height, buildTime, cost and costToUpgrade parameters
     *
     * @param name          The name of the Weapon
     * @param speed         The speed of the Weapon
     * @param damage        The damage the Weapon inflicts
     * @param rateOfFire    The rate of fire of the Weapon
     * @param health        The health of the Weapon
     * @param range         The range of the Weapon
     * @param chanceToHit   The chance of the Weapon to score a hit
     * @param buildTime     The build time of the Weapon
     * @param cost          The cost of the Weapon
     * @param costToUpgrade The cost to upgrade to the Weapon
     * @param texture       The texture graphic of the Weapon
     */
    public Weapon(String name, double speed, double damage, double rateOfFire, double health, double range, double chanceToHit,
                  double buildTime, int cost, int costToUpgrade, int texture) {
        super(name, speed, damage, rateOfFire, health, range, chanceToHit, 60, 60, texture, false);
        this.buildTime = buildTime;
        this.cost = cost;
        this.costToUpgrade = costToUpgrade;
        remainingBuildTime = buildTime;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        renderParticleEffect(delta, batch);
        batch.draw(texture, x, y, width, height);
        batch.end();
        act(delta);
        checkInput();
    }

    @Override
    public void act(float delta) {
        if (built && !checkZeroHealth()) {
            switch (name) {
                case "Rapid Fire Weapon":
                    if (!overloaded) {
                        if (isAdjacent) {
                            overloaded = rapidFireHelper();
                        } else {
                            overloaded = false;
                            unitFireHelper(40, 0);
                        }
                    }
                default:
                    if (!isAdjacent) {
                        unitFireHelper(40, 0);
                    } else {
                        adjacentActor.takeDamage(fire());
                    }
                    break;
            }
        } else {
            decrementBuildTimer();
        }
    }

    /**
     * Decrements the build timer by 0.5
     * if the time since the last call to the method is greater than or equal to 500 milliseconds.
     * If afterwards the build timer is less than or equal to 0 then built is set to true.
     */
    public void decrementBuildTimer() {
        if (System.currentTimeMillis() - startTime >= 500) {
            if (remainingBuildTime > 0) {
                remainingBuildTime -= 0.5;
            }
            if (remainingBuildTime <= 0) {
                built = true;
            }
            startTime = System.currentTimeMillis();
        }
    }

    /**
     * Returns the build time for the Weapon
     *
     * @return buildTime
     */
    public double getBuildTime() {
        return buildTime;
    }

    /**
     * Returns the remaining build time of the Weapon.
     *
     * @return remainingBuildTime
     */
    public double getRemainingBuildTime() {
        return remainingBuildTime;
    }

    /**
     * Returns the cost of building the weapon.
     *
     * @return cost.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Returns the cost to upgrade the weapon.
     *
     * @return costToUpgrade
     */
    public int getCostToUpgrade() {
        return costToUpgrade;
    }

    /**
     * Returns a boolean stating if the Unit has been built or not.
     *
     * @return built
     */
    public boolean getBuilt() {
        return built;
    }

    /**
     * Upgrades the weapon's damage by 10%, and increases cost to upgrade 25%.
     */
    public void upgradeWeapon() {
        Double newUpgradeCost = Math.ceil((costToUpgrade * 1.25));
        costToUpgrade = newUpgradeCost.intValue();
        damage = Math.ceil((damage * 1.1));
    }

    /**
     * Returns if the Weapon is overloaded.
     *
     * @return Overloaded state of the Weapon.
     */
    public boolean getOverloaded() {
        return overloaded;
    }

    /**
     * Sets if the Weapon is overloaded or not.
     *
     * @param overloaded state of the Weapon.
     */
    public void setOverloaded(boolean overloaded) {
        this.overloaded = overloaded;
    }
}