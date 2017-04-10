package com.aston.group.stationdefender.tests.tests;

import com.aston.group.stationdefender.actors.Alien;
import com.aston.group.stationdefender.config.Constants;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SelfDestructUnitTests {
    private Alien testKamikaze;

    @Before
    public void setUp() {
        testKamikaze = new Alien("Kamikaze Alien", -100, 100, 1, Constants.UNIT_HEALTH, 1.0, 0.9, 100, 38, 14);
        ;
    }

    @Test
    public void testKamikazeConstructor() {
        assertEquals("Kamikaze Alien", testKamikaze.getName());
        assertEquals(4, testKamikaze.getSpeed(), 0);
        assertEquals(100, testKamikaze.getDamage(), 0);
        assertEquals(1, testKamikaze.getRateOfFire(), 0);
        assertEquals(100, testKamikaze.getHealth(), 0);
        assertEquals(0.9, testKamikaze.getChanceToHit(), 0);
        assertEquals(1.0, testKamikaze.getRange(), 0);
    }

    @Test
    public void testAlienSelfDestruct() {
        assertEquals(true, testKamikaze.getExists());
        testKamikaze.act(0);
        assertEquals(false, testKamikaze.getExists());
    }
}