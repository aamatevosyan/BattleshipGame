package hse.edu.battleship.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubmarineTest {

    @Test
    void testToString() {
        Submarine ship = new Submarine();
        assertEquals("S", ship.toString());
    }

    @Test
    void getLength() {
        Submarine ship = new Submarine();
        assertEquals(1, ship.getLength());
    }

    @Test
    void getShipType() {
        Submarine ship = new Submarine();
        assertEquals("submarine", ship.getShipType());
    }
}