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
    private int textY;

    /**
     * Creates a new HudUnit with given X and Y co-ordinates
     *
     * @param actor The Actor to assign the HUD to
     * @param x     The X co-ordinate of the HudUnit
     * @param y     The Y co-ordinate of the HudUnit
     */
    public HudUnit(Actor actor, int x, int y) {
        super(x, y);
        unit = (Unit) actor;
        title = unit.getName();
        width = 150;
        height = 110;
        if (title.contains("Weapon"))
            height += 20;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        textY = (height + y) - 25;
        font.draw(batch, "Damage: " + unit.getDamage(), x + 5, textY);
        restartBatch();
        font.draw(batch, "Health: " + unit.getHealth(), x + 5, textY);
        restartBatch();
        if (title.contains("Weapon")) {
            font.draw(batch, "Cost: " + ((Weapon) unit).getCost(), x + 5, textY);
            restartBatch();
        }
        font.draw(batch, "Range: " + unit.getRange(), x + 5, textY);
        restartBatch();
        font.draw(batch, "ROF: " + unit.getRateOfFire(), x + 5, textY);
        batch.end();
    }

    /**
     * Restarts the SpriteBatch to allow more objects to be drawn on the screen
     */
    private void restartBatch() {
        batch.end();
        batch.begin();
        textY -= 20;
    }
}