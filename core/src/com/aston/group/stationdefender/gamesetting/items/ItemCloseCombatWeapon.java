package com.aston.group.stationdefender.gamesetting.items;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.CloseCombatWeapon;
import com.aston.group.stationdefender.callbacks.ItemCallback;
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
        return new CloseCombatWeapon();
    }
}