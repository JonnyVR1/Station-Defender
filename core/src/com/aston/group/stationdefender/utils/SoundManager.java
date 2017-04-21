package com.aston.group.stationdefender.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * This enum manages sound playing for the game
 *
 * @author Mohammed Foysal
 */
public enum SoundManager {
    BACKGROUND_MUSIC,
    GUN_SHOT,
    EXPLOSION;

    /**
     * Play the sound matching a given Enum value
     *
     * @param soundManager The Enum value of the sound to play
     */
    public static void playSound(SoundManager soundManager) {
        Music music = null;
        float volume = 0.2f;
        switch (soundManager) {
            case BACKGROUND_MUSIC:
                music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Background_Music.ogg"));
                music.setLooping(true);
                volume = 0.1f;
                break;
            case GUN_SHOT:
                music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Gun_Shot.mp3"));
                break;
            case EXPLOSION:
                music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Explosion.mp3"));
                break;
        }
        if (music != null) {
            music.setVolume(volume);
            music.play();
            music.setOnCompletionListener(Music::dispose);
        }
    }
}