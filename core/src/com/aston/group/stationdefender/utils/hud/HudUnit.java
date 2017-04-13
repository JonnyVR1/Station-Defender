package com.aston.group.stationdefender.utils.hud;

import com.aston.group.stationdefender.actors.Actor;
import com.aston.group.stationdefender.actors.Unit;
import com.aston.group.stationdefender.actors.Weapon;

/**
 * HudUnit is a HudElement specific to the Unit objects
 *
 * @author Mohammad Foysal
 */
public class HudUnit extends HudContainer {
    private final Unit unit;
    private int yPos;

    /**
     * Creates a new HudWeapon with default X and Y co-ordinates of '0'
     */
    public HudUnit(Actor actor) {
        super();
        unit = (Unit) actor;
        title = unit.getName();
        width = 150;
        height = 110;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        yPos = (height + y) - 25;
        font.draw(batch, "Damage: " + unit.getDamage(), x + 5, yPos);
        restartBatch();
        font.draw(batch, "Health: " + unit.getHealth(), x + 5, yPos);
        restartBatch();
        if (unit instanceof Weapon) {
            font.draw(batch, "Cost: " + ((Weapon) unit).getCost(), x + 5, yPos);
            restartBatch();
        }
        font.draw(batch, "Range: " + unit.getRange(), x + 5, yPos);
        restartBatch();
        font.draw(batch, "ROF: " + unit.getRateOfFire(), x + 5, yPos);
        batch.end();
    }

    /**
     * Restarts the SpriteBatch to allow more objects to be drawn on the screen
     */
    private void restartBatch() {
        batch.end();
        batch.begin();
        yPos -= 20;
    }
}