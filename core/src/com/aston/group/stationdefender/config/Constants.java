package com.aston.group.stationdefender.config;

import com.badlogic.gdx.graphics.Color;

/**
 * This class is for constant known variables used throughout the game
 *
 * @author Mohammed Foysal
 */
public final class Constants {
    public static final boolean DEBUG = false;

    // 16/9 Aspect Ratio
    public static final int SCREEN_WIDTH = 1024;
    public static final int SCREEN_HEIGHT = 576;

    public static final String GAME_NAME = "Station Defenders";

    public static final String[] MENU_ITEMS = {
            "Background",
            "How to Play",
            "Play",
            "Exit"
    };

    public static final String BACKGROUND = "During an era of peace, 2 centuries into the future humans are attacked\n " +
            "on a space station by their worst fear. Aliens are trying to invade the space\n " +
            "station and the battle has commenced. In order for the survival of the\n " +
            "human race we must defend our most valued Command Centre,\n " +
            "without this the security of our world will be compromised.\n " +
            "Soldiers of various abilities have been brought to the front-line,\n " +
            "equipped with different resources some effective at defense whilst others\n " +
            "at attacking the extraterrestrial species.\n " +
            "However the Aliens are not to be underestimated, attacking in\n " +
            "numbers and with full force.\n\n" +
            "Only with quick and precise strategic thinking can our world be saved!";

    public static final String BACK = "Back";

    public static final String CONTINUE = "Continue";

    public static final String MENU = "Menu";

    public static final String INSTRUCTIONS = "The aim of the game is to defend the command centre from the swarm \nof aliens abroad the space station." +
            "To defend the station from the aliens\nyou can use a range of weapons which can be found in your inventory\nlocated on the bottom left." +
            "\n\nUse your resources carefully as all weapons cost money to build.\n" +
            "Once a weapon is selected place it on a tile on the board and start\ndefeating the aliens, the more aliens you kill the more money you receive\nto use on better weapons.";

    public static final int TILE_WIDTH = 80;
    public static final int TILE_HEIGHT = 80;
    public static final int TILE_AMOUNT = 11;
    public static final int LANE_AMOUNT = 4;

    public static final int TOWER_HEALTH = 1000;
    public static final int UNIT_HEALTH = 100;
    public static final int WEAPON_HEALTH = 100;
    public static final int DEFAULT_DAMAGE = 50;

    public static final int START_MONEY = 50;
    public static final int MONEY_REGENERATION = 1;
    public static final int BOSS_DESTROY_MONEY_REGENERATION = 20;
    public static final int ADD_SCORE_AMOUNT = 10;

    public static final Color primaryColor = Color.valueOf("#37474F");
    public static final Color primaryDarkColor = Color.valueOf("#263238");

    public static final float VERSION = 2.3f;

    public static final String prefs = "PREFS";
}