package com.aston.group.stationdefender.actors.helpers;

import com.aston.group.stationdefender.utils.SoundManager;
import com.aston.group.stationdefender.utils.TextureManager;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Helper class to deal with the explosion animation effect.
 *
 * @author Jonathon Fitch
 */
public class ParticleEffectHelper {
    private ParticleEffect particleEffect;

    public void destroy(int x, int y) {
        SoundManager.INSTANCE.playSound(3);
        particleEffect = TextureManager.INSTANCE.loadParticleEffect(1);
        particleEffect.getEmitters().first().setPosition(x, y);
        particleEffect.start();
    }

    /**
     * Render the particle effect
     *
     * @param delta The time in seconds since the last render
     * @param batch The SpriteBatch to render the particle effect on
     */
    public void renderParticleEffect(float delta, SpriteBatch batch, int x, int y) {
        if (particleEffect != null) {
            particleEffect.update(delta);
            particleEffect.setPosition(x, y);
            particleEffect.draw(batch);
            if (particleEffect.isComplete())
                particleEffect.dispose();
        }
    }
}