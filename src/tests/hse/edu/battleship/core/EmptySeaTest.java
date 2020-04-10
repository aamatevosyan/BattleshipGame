package hse.edu.battleship.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EmptySeaTest {

    @Test
    void testToString() {
        EmptySea ship = new EmptySea();
        assertEquals(".", ship.toString());
    }

    @Test
    void getLength() {
        EmptySea ship = new EmptySea();
        assertEquals(1, ship.getLength());
    }

    @Test
    void getShipType() {
        EmptySea ship = new EmptySea();
        assertEquals("empty", ship.getShipType());
    }

    @Test
    void isSunk() {
        EmptySea ship = new EmptySea();
        assertFalse(ship.isSunk());
    }

    @Test
    void shootAt() {
        EmptySea ship = new EmptySea();
        assertEquals(".", ship.codeAt(0, 0));

        ship.shootAt(0, 0);
        assertEquals("-", ship.codeAt(0, 0));
    }

    @Test
    void codeAt() {
        EmptySea ship = new EmptySea();
        assertEquals(".", ship.codeAt(0, 0));
    }
}