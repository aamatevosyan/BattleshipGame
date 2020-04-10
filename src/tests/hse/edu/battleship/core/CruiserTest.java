package hse.edu.battleship.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CruiserTest {

    @Test
    void testToString() {
        Cruiser ship = new Cruiser();
        assertEquals("S", ship.toString());
    }

    @Test
    void getLength() {
        Cruiser ship = new Cruiser();
        assertEquals(3, ship.getLength());
    }

    @Test
    void getShipType() {
        Cruiser ship = new Cruiser();
        assertEquals("cruiser", ship.getShipType());
    }
}