package com.aston.group.stationdefender.utils.hud;

import com.aston.group.stationdefender.utils.MouseInput;

/**
 * Abstract class for HudElements
 *
 * @author Mohammad Foysal
 */
public abstract class HudElement {
    final int x, y;
    int width = 400;
    int height = 200;
    String title = "Blank Container";

    /**
     * Creates a new HudElement with given X and Y co-ordinates
     *
     * @param x The X co-ordinate of the HudElement
     * @param y The Y co-ordinate of the HudElement
     */
    HudElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Render the HudElement
     *
     * @param delta The time in seconds since the last render
     */
    public abstract void render(float delta);

    /**
     * Checks whether the HudElement is colliding with another object
     *
     * @return True if the HudElement is colliding with another object, false if it isn't
     */
    public boolean isColliding() {
        return MouseInput.isColliding(x, y, width, height);
    }
}