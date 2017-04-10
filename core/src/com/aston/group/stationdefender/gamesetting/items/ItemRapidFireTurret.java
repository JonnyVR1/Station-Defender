package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Weapon;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.gamesetting.items.helpers.Items;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemRapidFireTurret represents a RapidFireTurret Item that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemRapidFireTurret extends Item {

    /**
     * Construct a new Rapid Fire Turret Item
     */
    public ItemRapidFireTurret() {
        super("Rapid Fire Turret");
        id = 3;
        cost = 15;
        texture = TextureManager.INSTANCE.loadTexture(13);
        placeable = true;
        sku = Items.RAPID_FIRE_WEAPON;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value, health);
    }

    @Override
    public Actor getPlaceableActor() {
        return new Weapon("Rapid Fire Weapon", 25, 5.0, 15.0, 10, 10, 0.5, 1.0, 15, 25, 13);
    }
}