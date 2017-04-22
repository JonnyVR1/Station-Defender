package com.aston.group.stationdefender.utils.hud;

import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.utils.FontManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 * This class is a container for the HUD
 *
 * @author Mohammad Foysal
 */
public class HudContainer extends HudElement {
    final BitmapFont font = FontManager.getFont(16);
    final SpriteBatch batch = GameEngine.getBatch();
    private final ShapeRenderer shapeRenderer = GameEngine.getShapeRenderer();

    /**
     * Creates a new HudContainer with given X and Y co-ordinates
     *
     * @param x The X co-ordinate of the HudContainer
     * @param y The Y co-ordinate of the HudContainer
     */
    HudContainer(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(float delta) {
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Constants.primaryColor);
        shapeRenderer.rect(x, y, width, height);
        shapeRenderer.setColor(Constants.primaryDarkColor);
        shapeRenderer.rect(x, (height + y) - 20, width, 20);
        shapeRenderer.end();
        batch.begin();
        font.draw(batch, title, x + 5, (height + y) - 5);
        batch.end();
    }
}