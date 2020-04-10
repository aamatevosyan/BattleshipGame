package hse.edu.battleship.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DestroyerTest {

    @Test
    void testToString() {
        Destroyer ship = new Destroyer();
        assertEquals("S", ship.toString());
    }

    @Test
    void getLength() {
        Destroyer ship = new Destroyer();
        assertEquals(2, ship.getLength());
    }

    @Test
    void getShipType() {
        Destroyer ship = new Destroyer();
        assertEquals("destroyer", ship.getShipType());
    }
}