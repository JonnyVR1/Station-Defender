package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Weapon;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.gamesetting.items.helpers.Items;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemTurret represents a Turret Weapon Item that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemTurret extends Item {

    /**
     * Construct a new Turret Item
     */
    public ItemTurret() {
        super("Turret");
        id = 2;
        cost = 10;
        texture = TextureManager.INSTANCE.loadTexture(8);
        placeable = true;
        sku = Items.TURRET;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value, health);
    }

    @Override
    public Actor getPlaceableActor() {
        return new Weapon();
    }
}