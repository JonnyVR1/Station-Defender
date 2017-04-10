package com.aston.group.stationdefender.tests.tests;

import com.aston.group.stationdefender.actors.Alien;
import com.aston.group.stationdefender.actors.Weapon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlienTest {
    private Weapon adjacentWep;
    private Alien alien;

    @Before
    public void setUp() {
        adjacentWep = new Weapon();
        alien = new Alien();
    }

    @Test
    public void testConstructor() {
        Alien a = new Alien();
        assertNotNull(a);
        assertEquals("Alien", alien.getName());
        assertEquals(1, alien.getSpeed(), 0);
        assertEquals(3, alien.getDamage(), 0);
        assertEquals(3, alien.getRateOfFire(), 0);
        assertEquals(15, alien.getHealth(), 0);
        assertEquals(1, alien.getRange(), 0);
    }

    @Test
    public void testDamageAndCheckHealth() {
        for (int i = 15; i > 0; i--) {
            assertEquals(i, alien.getHealth(), 0);
            assertEquals(false, alien.checkZeroHealth());
            alien.takeDamage(1);
        }
        assertEquals(0, alien.getHealth(), 0);
        assertEquals(true, alien.checkZeroHealth());
    }

    @Test
    public void testFiring() {
        double damageDealt = alien.fire();
        assertTrue((damageDealt >= 0) && (damageDealt <= 9));
    }

    @Test
    public void testAdjacent() {
        Alien adjacentAlien = new Alien();
        assertEquals(false, alien.isAdjacent());
        assertEquals(null, alien.getAdjacentActor());
        alien.setAdjacentActor(adjacentWep);
        assertEquals(true, alien.isAdjacent());
        assertEquals(adjacentWep, alien.getAdjacentActor());
        alien.setAdjacentActor(adjacentAlien);
        assertEquals(true, alien.isAdjacent());
        assertEquals(adjacentAlien, alien.getAdjacentActor());
        alien.setAdjacentActor(null);
        assertEquals(false, alien.isAdjacent());
        assertEquals(null, alien.getAdjacentActor());
    }

    @Test
    public void testDamageDealing() {
        assertEquals(10, adjacentWep.getHealth(), 0);
        alien.setAdjacentActor(adjacentWep);
        assertEquals(adjacentWep, alien.getAdjacentActor());
        alien.act(0.1f);
        assertTrue((adjacentWep.getHealth() <= 10));
    }
}