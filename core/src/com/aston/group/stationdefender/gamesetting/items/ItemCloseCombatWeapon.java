package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Weapon;
import com.aston.group.stationdefender.callbacks.ItemCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.gamesetting.items.helpers.Items;
import com.aston.group.stationdefender.utils.TextureManager;

/**
 * ItemCloseCombatWeapon represents a CloseCombatWeapon Item that can be used within the game
 *
 * @author Mohammed Foysal
 */
public class ItemCloseCombatWeapon extends Item {

    /**
     * Construct a new CloseCombatWeapon Item
     */
    public ItemCloseCombatWeapon() {
        super("Close Combat Weapon");
        id = 4;
        cost = 15;
        texture = TextureManager.INSTANCE.loadTexture(25);
        placeable = true;
        sku = Items.CLOSE_COMBAT_WEAPON;
    }

    @Override
    public void useItem(ItemCallback itemCallback) {
        if (itemCallback != null)
            itemCallback.onUse(placeable, cost, value, health);
    }

    @Override
    public Actor getPlaceableActor() {
        return new Weapon("Close Combat Weapon", 25, 50.0, 2.0, Constants.UNIT_HEALTH, 4.0, 7.0, 2.0, 15, 25, 25);
    }
}