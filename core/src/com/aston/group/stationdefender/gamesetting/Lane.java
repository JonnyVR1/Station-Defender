package com.aston.group.stationdefender.gamesetting;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.actors.helpers.UnitFactory;
import com.aston.group.stationdefender.callbacks.LaneCallback;
import com.aston.group.stationdefender.callbacks.UnitCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.helpers.Projectile;
import com.aston.group.stationdefender.gamesetting.helpers.Tile;
import com.aston.group.stationdefender.gamesetting.items.Item;
import com.aston.group.stationdefender.gamesetting.items.helpers.ItemFactory;
import com.aston.group.stationdefender.utils.MouseInput;
import com.aston.group.stationdefender.utils.ProjectileFactory;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Lane class
 *
 * @author Jonathon Fitch
 * @author Twba Alshaghdari
 */
public class Lane implements UnitCallback {
    private final int y;
    private final Array<Tile> tiles = new Array<>();
    private final Array<Actor> actors = new Array<>();
    private final Array<Item> itemDrops = new Array<>();
    private final ProjectileFactory projectileFactory = new ProjectileFactory();
    private final LaneCallback laneCallback;
    private int width;
    private boolean overrun;
    private boolean cleared;
    private int alienAmount;
    private long lastRenderTime = System.currentTimeMillis();

    /**
     * Construct a new Lane
     *
     * @param laneCallback THe LaneCallback to be used for the Lane
     * @param y            The Y co-ordinate of the Lane
     * @param difficulty   The difficulty of the Level
     */
    Lane(LaneCallback laneCallback, int y, double difficulty) {
        this.laneCallback = laneCallback;
        this.y = y;

        Tile[] tile = new Tile[Constants.TILE_AMOUNT];
        int tileX = 100;
        int itemTileProbability = 2 * laneCallback.getLevelNumber();
        int invalidTileProbability = 14 / laneCallback.getLevelNumber();
        Random rand = new Random();
        for (int i = 0; i < Constants.TILE_AMOUNT; i++) {
            tile[i] = new Tile(tileX, y);

            // Probability will be 1 / tileProbability
            tile[i].setHasItem(rand.nextInt(itemTileProbability) == 0);
            tile[i].setInvalid(rand.nextInt(invalidTileProbability) == 0);
            tileX += Constants.TILE_WIDTH;
            width += Constants.TILE_WIDTH;
        }
        tiles.addAll(tile);

        alienAmount = (int) difficulty;
    }

    /**
     * Places a Unit in a Lane using the Tile as a helper
     *
     * @param actor The Actor to place
     * @param x     The X co-ordinate of the Tile
     * @param y     The Y co-ordinate of the Tile
     * @return True if the placement was successful, false if the placement was unsuccessful
     */
    boolean place(Actor actor, int x, int y) {
        for (int i = 0; i < tiles.size; i++) {
            if (tiles.get(i).isColliding(x, y, 1, 1) && !isTileOccupied(i) && !tiles.get(i).isInvalid()) {
                actor.setX(tiles.get(i).getCenterX() - (actor.getWidth() / 2));
                actor.setY(tiles.get(i).getCenterY() - (actor.getHeight() / 2));
                if (tiles.get(i).isHasItem()) {
                    Item item = ItemFactory.getRandomItem();
                    laneCallback.collectItem(item);
                }
                actors.add(actor);
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a Unit is on a tile
     *
     * @param tileIndex The index of the Tile to check
     * @return true if a Unit is on the Tile, false if a Unit is not on the Tile
     */
    private boolean isTileOccupied(int tileIndex) {
        return tiles.get(tileIndex) != null && IntStream.range(0, actors.size).anyMatch(i -> tiles.get(tileIndex).isColliding(actors.get(i)));
    }

    /**
     * Render the Lane.
     *
     * @param delta - The time in seconds since the last render.
     */
    public void render(float delta) {
        for (Tile tile : tiles) {
            tile.render();
        }
        //Units
        for (Actor actor : actors) {
            actor.render(delta);
            if (actor.isUnit()) {
                ((Unit) actor).setUnitCallback(this);
            }
        }

        //Check if Units are adjacent. if they are, share the adjacent actor with each other
        for (int i = 0; i < actors.size; i++) {
            if (actors.get(i).isUnit()) {
                Actor actor = actors.get(i);
                Actor adjacentActor = null;
                for (int j = 0; j < actors.size; j++) {
                    if (i != j && actors.get(j).isUnit()) {
                        Actor temp = actors.get(j);
                        if (((Unit) actor).isUnitAdjacent(temp)) {
                            adjacentActor = temp;
                            break;
                        }
                    }
                }

                if (adjacentActor != null) {
                    ((Unit) actor).setIsAdjacent(true);
                } else {
                    ((Unit) actor).setIsAdjacent(false);
                }
                ((Unit) actor).setAdjacentActor(adjacentActor);

                //Check if aliens are near tower
                if (((Unit) actor).isFacingLeft() && laneCallback.isTowerColliding(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight())) {
                    laneCallback.towerTakeDamage(((Unit) actor).getDamage());
                    overrun = true;
                    ((Unit) actor).destroy();
                    actors.removeIndex(i);
                }
            }
        }

        //Remove Dead Units
        Iterator<Actor> unitsIterator = actors.iterator();
        while (unitsIterator.hasNext()) {
            Actor actor = unitsIterator.next();
            if (actor.isUnit()) {
                if (!actor.getExists()) {
                    dropItem(ItemFactory.getItemByChance(), actor.getX(), actor.getY());
                    ((Unit) actor).setHealth(-1);
                }
                if (((Unit) actor).getHealth() <= 0) {
                    unitsIterator.remove();
                }
            }
        }

        //Spawn New Aliens
        if (System.currentTimeMillis() - lastRenderTime > 2200 + Math.random() * 3000) {
            if (alienAmount > 0) {
                Actor actor = UnitFactory.getRandomEnemy();
                if (Objects.equals(actor.getName(), "Mine"))
                    actor.setX(getRandomTileCenterX() - (actor.getHeight() / 2));
                else
                    actor.setX(getLastTileCenterX() - (actor.getWidth() / 2));
                actor.setY(getLastTileCenterY() - (actor.getHeight() / 2));
                actors.add(actor);
                alienAmount--;
            }
            lastRenderTime = System.currentTimeMillis();
        }

        //Draw Projectiles
        projectileFactory.render(delta);
        projectileCollision(actors, null);

        //Check if lane is cleared
        if (isLaneCleared() && alienAmount <= 0) {
            cleared = true;
        }

        //Render item drops
        for (int i = 0; i < itemDrops.size; i++) {
            itemDrops.get(i).render();
            if (Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && itemDrops.get(i).isJustSpawned() && MouseInput.isColliding(itemDrops.get(i).getX(), itemDrops.get(i).getY(), itemDrops.get(i).getWidth(), itemDrops.get(i).getHeight())) {
                laneCallback.collectItem(itemDrops.get(i));
                removeItem(i);
            }
        }
    }

    /**
     * Returns the X co-ordinate of the center of the last Tile
     *
     * @return The X co-ordinate of the center of the last Tile
     */
    private int getLastTileCenterX() {
        if (tiles.size > 0)
            return tiles.get(tiles.size - 1).getCenterX();
        else
            return 0;
    }

    /**
     * Returns the X co-ordinate of the center of a random Tile
     *
     * @return The X co-ordinate of the center of a random Tile
     */
    private int getRandomTileCenterX() {
        if (tiles.size > 0)
            return tiles.get(ThreadLocalRandom.current().nextInt(1, tiles.size)).getCenterX();
        else
            return 0;
    }

    /**
     * Returns whether the lane is cleared of Aliens or not
     *
     * @return True if the lane is cleared of Aliens, false if not
     */
    private boolean isLaneCleared() {
        return IntStream.range(0, actors.size).noneMatch(i -> {
            if (actors.get(i).isUnit()) {
                Unit unit = (Unit) actors.get(i);
                return unit.isFacingLeft();
            }
            return false;
        });
    }

    /**
     * Returns the Y co-ordinate of the center of the last Tile
     *
     * @return The Y co-ordinate of the center of the last Tile
     */
    private int getLastTileCenterY() {
        if (tiles.size > 0) {
            return tiles.get(tiles.size - 1).getCenterY();
        } else {
            return 0;
        }
    }

    /**
     * Check if an objects X &amp; Y co-ordinates or width &amp; height
     * overlaps the Lanes X &amp; Y co-ordinates, or width &amp; height
     *
     * @param x The X co-ordinate of the object to check
     * @param y The Y co-ordinate of the object to check
     * @return true if the values overlap, false if the values do not overlap
     */
    boolean isColliding(int x, int y) {
        int height = Constants.TILE_HEIGHT;
        int laneX = 100;
        return x + 1 > laneX && x < laneX + this.width && y + 1 > this.y && y < this.y + height;
    }

    @Override
    public void onFire(int x, int y, double speed, double damage) {
        projectileFactory.shootBullet(x, y, speed, damage);
    }

    /**
     * Drop an Item on the Lane
     *
     * @param item The Item to drop
     * @param x    The X co-ordinate to drop the Item
     * @param y    The Y co-ordinate to drop the Item
     */
    private void dropItem(Item item, int x, int y) {
        if (item != null) {
            item.setX(x);
            item.setY(y);
            item.setJustSpawned();
            itemDrops.add(item);
        }
    }

    /**
     * Remove an Item from the Lane
     *
     * @param index The index of the Item to be removed
     */
    private void removeItem(int index) {
        itemDrops.removeIndex(index);
    }

    /**
     * Dispose of unused resources
     */
    public void dispose() {
        for (Tile tile : tiles) {
            tile.dispose();
        }
        for (Actor actor : actors) {
            actor.dispose();
        }
    }

    /**
     * Returns whether a Lane is overrun with Aliens or not
     *
     * @return true if a Lane is overrun with Aliens, false if the Lane is not overrun by Aliens
     */
    boolean isOverrun() {
        return overrun;
    }

    /**
     * Returns whether a Lane is cleared or not and there are no more Aliens onscreen
     *
     * @return true if a Lane is cleared, false if a Lane is not cleared
     */
    boolean isCleared() {
        return cleared;
    }

    /**
     * Checks if projectiles are colliding with a Unit or an array of Units
     *
     * @param actors   The array of Units to cycle through
     * @param bossUnit The singular boss Unit to check for collisions
     */
    void projectileCollision(Array<Actor> actors, Actor bossUnit) {
        for (int i = 0; i < projectileFactory.getProjectiles().size; i++) {
            if (actors != null) {
                for (int j = 0; j < actors.size; j++) {
                    if (actors.get(i).isUnit()) {
                        Actor actor = actors.get(j);
                        projectileCollisionHelper(projectileFactory.getProjectiles().get(i), actor, false);
                    }
                }
            } else {
                projectileCollisionHelper(projectileFactory.getProjectiles().get(i), bossUnit, true);
            }
        }
    }

    /**
     * Helper method to avoid duplicate code in projectileCollision()
     *
     * @param projectile  The Projectile to check for collisions
     * @param actor       The Unit to check for collisions against the Projectile
     * @param isBossEnemy Whether the Unit is a Boss Enemy or not
     */
    private void projectileCollisionHelper(Projectile projectile, Actor actor, boolean isBossEnemy) {
        if (projectile.isColliding(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight())) {
            projectile.setDead();
            double damage = projectile.getDamage();
            if (((Unit) actor).getHealth() - damage <= 0) {
                if (isBossEnemy)
                    laneCallback.addMoney(Constants.BOSS_DESTROY_MONEY_REGENERATION);
                else {
                    laneCallback.addMoney(Constants.MONEY_REGENERATION);
                    laneCallback.addScore(Constants.ADD_SCORE_AMOUNT);
                }
            }
            actor.takeDamage(damage);
        }
        if (laneCallback.isTowerColliding(projectile.getX(), projectile.getY(), projectile.getWidth(), projectile.getHeight())) {
            laneCallback.towerTakeDamage(projectile.getDamage());
            projectile.setDead();
        }
    }
}