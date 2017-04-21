package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

/**
 * This enum manages texture loading for the game
 *
 * @author Jonathon Fitch
 */
public enum TextureManager {
    BACKGROUND_TITLE,
    BACKGROUND_1,
    BACKGROUND_2,
    BACKGROUND_3,
    BACKGROUND_4,
    BACKGROUND_5,
    BLACK_HOVER,
    QUICK_SLOT,
    TILE,
    TOWER,
    ALIEN,
    CLOSE_COMBAT_ALIEN,
    KAMIKAZE_ALIEN,
    MINE_ALIEN,
    RAPID_FIRE_ALIEN,
    WEAPON,
    CLOSE_COMBAT_WEAPON,
    RAPID_FIRE_WEAPON,
    BOSS_ALIEN_1,
    BOSS_ALIEN_2,
    BOSS_ALIEN_3,
    BOSS_ALIEN_4,
    PROJECTILE,
    ITEM_BANDAGES,
    ITEM_BRICKS,
    ITEM_CEMENT,
    ITEM_CREDIT,
    ITEM_HEALTH,
    ITEM_WATER,
    ITEM_WOOD,
    UNKNOWN;


    /**
     * Load the texture matching a given ID
     *
     * @param textureManager The ID number of the texture to load
     * @return The texture matching the ID
     */
    public static Texture loadTexture(TextureManager textureManager) {
        Texture texture = null;
        switch (textureManager) {
            case BACKGROUND_TITLE:
                texture = new Texture(Gdx.files.internal("textures/intro-back.jpg"));
                break;
            case BACKGROUND_1:
                texture = new Texture(Gdx.files.internal("textures/level-background.png"));
                break;
            case BACKGROUND_2:
                texture = new Texture(Gdx.files.internal("textures/back.jpg"));
                break;
            case BACKGROUND_3:
                texture = new Texture(Gdx.files.internal("textures/space.png"));
                break;
            case BACKGROUND_4:
                texture = new Texture(Gdx.files.internal("textures/space2.png"));
                break;
            case BACKGROUND_5:
                texture = new Texture(Gdx.files.internal("textures/space3.png"));
                break;
            case BLACK_HOVER:
                texture = new Texture(Gdx.files.internal("textures/black.jpg"));
                break;
            case QUICK_SLOT:
                texture = new Texture(Gdx.files.internal("data/uiskin.png"));
                break;
            case TILE:
                texture = new Texture(Gdx.files.internal("textures/tile.png"));
                break;
            case TOWER:
                texture = new Texture(Gdx.files.internal("textures/tower.png"));
                break;
            case ALIEN:
                texture = new Texture(Gdx.files.internal("textures/enemy.png"));
                break;
            case CLOSE_COMBAT_ALIEN:
                texture = new Texture(Gdx.files.internal("textures/CloseCombatAlien.png"));
                break;
            case KAMIKAZE_ALIEN:
                texture = new Texture(Gdx.files.internal("textures/bomber-enemy.png"));
                break;
            case MINE_ALIEN:
                texture = new Texture(Gdx.files.internal("textures/landmine.png"));
                break;
            case RAPID_FIRE_ALIEN:
                texture = new Texture(Gdx.files.internal("textures/rf-enemy.png"));
                break;
            case BOSS_ALIEN_1:
                texture = new Texture(Gdx.files.internal("textures/boss1.png"));
                break;
            case BOSS_ALIEN_2:
                texture = new Texture(Gdx.files.internal("textures/boss2.png"));
                break;
            case BOSS_ALIEN_3:
                texture = new Texture(Gdx.files.internal("textures/boss3.png"));
                break;
            case BOSS_ALIEN_4:
                texture = new Texture(Gdx.files.internal("textures/boss4.png"));
                break;
            case WEAPON:
                texture = new Texture(Gdx.files.internal("textures/turret.png"));
                break;
            case CLOSE_COMBAT_WEAPON:
                texture = new Texture(Gdx.files.internal("textures/cc-turret.png"));
                break;
            case RAPID_FIRE_WEAPON:
                texture = new Texture(Gdx.files.internal("textures/rf-turret.png"));
                break;
            case PROJECTILE:
                texture = new Texture(Gdx.files.internal("textures/projectile.png"));
                break;
            case ITEM_BANDAGES:
                texture = new Texture(Gdx.files.internal("textures/item-bandages.png"));
                break;
            case ITEM_BRICKS:
                texture = new Texture(Gdx.files.internal("textures/item-bricks.png"));
                break;
            case ITEM_CEMENT:
                texture = new Texture(Gdx.files.internal("textures/item-cement.png"));
                break;
            case ITEM_CREDIT:
                texture = new Texture(Gdx.files.internal("textures/item-credits.png"));
                break;
            case ITEM_HEALTH:
                texture = new Texture(Gdx.files.internal("textures/item-health.png"));
                break;
            case ITEM_WATER:
                texture = new Texture(Gdx.files.internal("textures/item-water.png"));
                break;
            case ITEM_WOOD:
                texture = new Texture(Gdx.files.internal("textures/item-wood.png"));
                break;
        }
        return texture;
    }

    /**
     * Load the animation matching a given ID
     *
     * @return The animation matching the given ID
     */
    public static ParticleEffect loadParticleEffect() {
        ParticleEffect particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("textures/explosion.animation"), Gdx.files.internal(""));
        return particleEffect;
    }
}