package com.aston.group.stationdefender.actors;

/**
 * Interface implemented by the Unit and Tower classes.
 * Used for entities within the game that can act and be destroyed.
 *
 * @author Jamie Ingram
 * @version 20/10/2016
 */
public interface Actor {

    /**
     * Render the Actor.
     *
     * @param delta - The time in seconds since the last render.
     */
    void render(float delta);

    /**
     * Returns the exist state of the Actor
     *
     * @return true if the Actor exists, false if the Actor does not exist
     */
    boolean getExists();

    /**
     * Causes the Units health to lower by the damage parameter.
     *
     * @param damage Causes the Unit's health to deplete.
     */
    void takeDamage(double damage);

    /**
     * Dispose of unused resources
     */
    void dispose();

    /**
     * Returns the height of the Unit
     *
     * @return The height of the Unit
     */
    int getHeight();

    /**
     * Returns the width of the Unit
     *
     * @return The width of the Unit
     */
    int getWidth();

    /**
     * Returns the X co-ordinate of the Unit
     *
     * @return The X co-ordinate of the Unit
     */
    int getX();

    /**
     * Sets the X co-ordinate of the Unit
     *
     * @param x The X co-ordinate of the Unit
     */
    void setX(int x);

    /**
     * Returns the Y co-ordinate of the Unit
     *
     * @return The Y co-ordinate of the Unit
     */
    int getY();

    /**
     * Sets the Y co-ordinate of the Unit
     *
     * @param y The Y co-ordinate of the Unit
     */
    void setY(int y);

    /**
     * Method for getting the name of the Actor.
     *
     * @return name of the Actor.
     */
    String getName();

    /**
     * Returns whether the Actor is a Unit or not
     *
     * @return Whether the Actor is a Unit or not
     */
    @SuppressWarnings("SameReturnValue")
    boolean isUnit();
}