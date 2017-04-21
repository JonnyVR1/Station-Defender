package com.aston.group.stationdefender.actors.helpers;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Alien;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.utils.TextureManager;

import java.util.Random;

/**
 * This class is responsible for handling what Units are created
 *
 * @author Mohammad Foysal
 */
public enum UnitFactory {
    ALIEN,
    CLOSE_COMBAT_ALIEN,
    KAMIKAZE,
    RAPID_FIRE_ALIEN,
    MINE;

    /**
     * Returns a new Enemy Unit
     *
     * @param unitFactory The set of Units to choose from
     * @return A new Enemy Unit
     */
    private static Actor getEnemy(UnitFactory unitFactory) {
        Actor actor = null;
        switch (unitFactory) {
            case ALIEN:
                actor = new Alien();
                break;
            case CLOSE_COMBAT_ALIEN:
                actor = new Alien("Close Combat Alien", -100, 60.0, 2, Constants.UNIT_HEALTH, 2, 7.0, 20, 20, TextureManager.CLOSE_COMBAT_ALIEN);
                break;
            case KAMIKAZE:
                actor = new Alien("Kamikaze Alien", -100, 100, 1, Constants.UNIT_HEALTH, 3, 0.9, 100, 38, TextureManager.KAMIKAZE_ALIEN);
                break;
            case RAPID_FIRE_ALIEN:
                actor = new Alien("Rapid Fire Alien", -125, 5.0, 10, Constants.UNIT_HEALTH, 12, 0.5, 100, 38, TextureManager.RAPID_FIRE_ALIEN);
                break;
            case MINE:
                actor = new Alien("Mine", 0, 60, 4.0, Constants.UNIT_HEALTH, 4, 0.9, 60, 50, TextureManager.MINE_ALIEN);
                break;
        }
        return actor;
    }

    /**
     * Returns a random Enemy Unit
     *
     * @return The new Enemy Unit
     */
    public static Actor getRandomEnemy() {
        int rand = (int) (Math.random() * (values().length));
        return getEnemy(values()[rand]);
    }

    /**
     * Returns a new Boss Enemy
     *
     * @return The new Boss Enemy unit
     */
    public static Actor getBossEnemy() {
        TextureManager[] bossEnemies = {
                TextureManager.BOSS_ALIEN_1,
                TextureManager.BOSS_ALIEN_2,
                TextureManager.BOSS_ALIEN_3,
                TextureManager.BOSS_ALIEN_4
        };
        int randomTexture = new Random().nextInt(bossEnemies.length);
        return new Alien("Boss Alien", -60, 200, 5, 800, 5.0, 8, 300, 225, bossEnemies[randomTexture]);
    }
}