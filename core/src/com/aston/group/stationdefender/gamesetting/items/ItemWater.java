package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemWater represents water that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemWater extends Item {

    /**
     * Construct a new Water Item
     */
    public ItemWater() {
        super("Water");
        id = 10;
        cost = 3;
        texture = TextureManager.INSTANCE.loadTexture(29);
        placeable = false;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value, health);
    }

    @Override
    public Actor getPlaceableActor() {
        // TODO: create a Water Bottle actor
        return null;
    }
}