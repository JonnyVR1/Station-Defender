package com.aston.group.stationdefender.screens;

import com.aston.group.stationdefender.callbacks.MenuCallback;
import com.aston.group.stationdefender.config.Constants;
import com.aston.group.stationdefender.engine.GameEngine;
import com.aston.group.stationdefender.utils.FontManager;
import com.aston.group.stationdefender.utils.TextureManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Objects;

/**
 * MenuScreen is the screen shown when pressing the menu button
 * from within the game.  It shows options such as play, exit, save etc.
 *
 * @author Jonathon Fitch
 */
public class MenuScreen implements Screen {
    private final SpriteBatch batch = GameEngine.getBatch();
    private final BitmapFont font = FontManager.getFont(50);
    private final TextButton playButton, exitButton;
    private final Stage stage = new Stage();
    private final Texture texture = TextureManager.loadTexture(TextureManager.BACKGROUND_TITLE);

    /**
     * Construct a new MenuScreen
     *
     * @param menuCallback The MenuCallback to use
     */
    public MenuScreen(MenuCallback menuCallback) {
        EventListener buttonListener = new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.graphics.setSystemCursor(SystemCursor.Hand);
                if (Objects.equals(actor, playButton)) {
                    menuCallback.onPlay(false);
                } else if (Objects.equals(actor, exitButton)) {
                    menuCallback.onExit();
                }
            }
        };

        Texture hoverTexture = TextureManager.loadTexture(TextureManager.BLACK_HOVER);
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.font = font;
        textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(hoverTexture));
        playButton = new TextButton(Constants.MENU_ITEMS[2], textButtonStyle);
        exitButton = new TextButton(Constants.MENU_ITEMS[3], textButtonStyle);
        playButton.addListener(buttonListener);
        exitButton.addListener(buttonListener);

        Table table = new Table();
        table.setFillParent(true);
        table.add(playButton).row();
        table.add(exitButton).row();

        Group background = new Group();
        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Image image = new Image();
        image.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        image.setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));

        background.addActor(image);
        stage.addActor(background);
        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        GameEngine.render();
        batch.begin();
        batch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        stage.act(delta);
        batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        GameEngine.update(width, height);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}