package com.aston.group.stationdefender.desktop;

import com.aston.group.stationdefender.Main;
import com.aston.group.stationdefender.config.Constants;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * The Launcher class for the game on Desktop PC.
 *
 * @author Jonathon Fitch
 */
public enum DesktopLauncher {
    ;

    public static void main(String... arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle(Constants.GAME_NAME);
        config.setWindowedMode(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        config.setWindowIcon("textures/intro-back.jpg");
        new Lwjgl3Application(new Main(), config);
    }
}