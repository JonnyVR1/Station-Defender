package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemBricks represents a brick Item that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemBricks extends Item {

    /**
     * Construct a new Bricks Item
     */
    public ItemBricks() {
        super("Bricks");
        id = 6;
        cost = 5;
        texture = TextureManager.INSTANCE.loadTexture(28);
        placeable = false;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value, health);
    }

    @Override
    public Actor getPlaceableActor() {
        // TODO: create a Bricks actor
        return null;
    }
}