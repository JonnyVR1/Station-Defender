package com.aston.group.stationdefender.actors.helpers;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Alien;
import com.aston.group.stationdefender.config.Constants;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is responsible for handling what Units are created
 *
 * @author Mohammad Foysal
 */
public class UnitFactory {

    /**
     * Returns a new Enemy Unit
     *
     * @param units The set of Units to choose from
     * @return A new Enemy Unit
     */
    private static Actor getEnemy(Units units) {
        switch (units) {
            case ALIEN:
                return new Alien();
            case CLOSE_COMBAT_ALIEN:
                return new Alien("Close Combat Alien", -100, 60.0, 2, Constants.UNIT_HEALTH, 1, 7.0, 20, 20, 22);
            case KAMIKAZE:
                return new Alien("Kamikaze Alien", -100, 100, 1, Constants.UNIT_HEALTH, 1.0, 0.9, 100, 38, 14);
            case RAPID_FIRE_ALIEN:
                return new Alien("Rapid Fire Alien", -125, 5.0, 10, Constants.UNIT_HEALTH, 2, 0.5, 100, 38, 12);
            case MINE:
                return new Alien("Mine", 0, 60, 4.0, Constants.UNIT_HEALTH, 4, 0.9, 60, 50, 11);
            default:
                return new Alien();
        }
    }

    /**
     * Returns a random Enemy Unit
     *
     * @return The new Enemy Unit
     */
    public static Actor getRandomEnemy() {
        int rand = (int) (Math.random() * (Units.values().length));
        return getEnemy(Units.values()[rand]);
    }

    /**
     * Returns a new Boss Enemy
     *
     * @return The new Boss Enemy unit
     */
    public static Actor getBossEnemy() {
        return new Alien("Alien", -60, 200, 5, 800, 5.0, 8, 300, 225, ThreadLocalRandom.current().nextInt(15, 19));
    }
}