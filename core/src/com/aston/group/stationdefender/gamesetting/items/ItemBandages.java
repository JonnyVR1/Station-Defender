package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemBandages represents a bandage Item that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemBandages extends Item {

    /**
     * Construct a new Bandages Item
     */
    public ItemBandages() {
        super("Bandages");
        id = 5;
        cost = 3;
        health = 5;
        texture = TextureManager.INSTANCE.loadTexture(26);
        placeable = false;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value, health);
    }

    @Override
    public Actor getPlaceableActor() {
        // TODO: create a Bandages actor
        return null;
    }
}