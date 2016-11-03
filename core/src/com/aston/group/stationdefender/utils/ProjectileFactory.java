package com.aston.group.stationdefender.utils;

import com.aston.group.stationdefender.gamesetting.helpers.Projectile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Pool;

import java.util.ArrayList;
import java.util.Iterator;

public class ProjectileFactory {

    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

    private final Pool<Projectile> projectilePool = new Pool<Projectile>() {
        @Override
        protected Projectile newObject() {
            return new Projectile();
        }
    };

    private Sound sound;

    public ProjectileFactory() {
        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/Gun_Shot.mp3"));
    }

    public void createBullet(int x, int y, int direction) {
        Projectile projectile = projectilePool.obtain();
        projectile.init(x, y);
        projectiles.add(projectile);
        sound.play();
    }

    public void render(float delta) {

        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).render(delta);
        }

        //Remove Dead Projectiles
        Iterator<Projectile> projectileIterator = projectiles.iterator();
        while (projectileIterator.hasNext()) {
            Projectile projectile = projectileIterator.next();

            if (!projectile.isAlive()) {
                projectileIterator.remove();
                projectilePool.free(projectile);
            }
        }
    }

    public void dispose(){
        sound.dispose();
    }
}