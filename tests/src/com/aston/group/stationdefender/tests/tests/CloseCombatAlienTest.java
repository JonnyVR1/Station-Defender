package com.aston.group.stationdefender.tests.tests;

import com.aston.group.stationdefender.actors.Alien;
import com.aston.group.stationdefender.config.Constants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CloseCombatAlienTest {
    private Alien alien;

    @Before
    public void setUp() {
        alien = new Alien("Close Combat Alien", -100, 60.0, 2, Constants.UNIT_HEALTH, 1, 7.0, 20, 20, 22);
    }

    @Test
    public void testConstructor() {
        assertEquals("Close Combat Alien", alien.getName());
        assertEquals(2.0, alien.getSpeed(), 0);
        assertEquals(60.0, alien.getDamage(), 0);
        assertEquals(100.0, alien.getHealth(), 0);
        assertEquals(7.0, alien.getChanceToHit(), 0);
    }

    @Test
    public void testDamageAndCheckHealth() {
        alien = new Alien("Close Combat Alien", -100, 60.0, 2, Constants.UNIT_HEALTH, 1, 7.0, 20, 20, 22);
        assertEquals(100, (int) alien.getHealth());
        for (int i = 100; i > 0; i--) {
            assertEquals(i, alien.getHealth(), 0);
            assertEquals(false, alien.checkZeroHealth());
            alien.takeDamage(1);
        }
        assertEquals(0, alien.getHealth(), 0);
        assertEquals(true, alien.checkZeroHealth());
    }
}