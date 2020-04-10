package hse.edu.battleship.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    @Test
    void okToPlaceShipAt() {
        Ocean ocean = new Ocean();
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        assertFalse(ship.okToPlaceShipAt(7, 7, true, ocean));
        assertTrue(ship.okToPlaceShipAt(7, 6, true, ocean));
    }

    @Test
    void placeShipAt() {
        Ocean ocean = new Ocean();
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        assertTrue(ship.okToPlaceShipAt(7, 6, true, ocean));
        ship.placeShipAt(7, 6, true, ocean);

        assertEquals("battleship", ocean.getAt(7, 6).getShipType());
    }

    @Test
    void shootAt() {
        Ship ship = new Submarine();

        assertFalse(ship.isSunk());
        ship.shootAt(0, 0);

        assertTrue(ship.isSunk());
    }

    @Test
    void isHit() {
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        ship.shootAt(0, 0);
        ship.shootAt(0, 2);

        assertTrue(ship.isHit(0, 0));
        assertFalse(ship.isHit(0, 1));
        assertTrue(ship.isHit(0, 2));
        assertFalse(ship.isHit(0, 3));
    }

    @Test
    void codeAt() {
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        ship.shootAt(0, 0);
        ship.shootAt(0, 2);

        assertEquals("S", ship.codeAt(0, 0));
        assertEquals(".", ship.codeAt(0, 1));
        assertEquals("S", ship.codeAt(0, 2));
        assertEquals(".", ship.codeAt(0, 3));
    }

    @Test
    void isSunk() {
        Ship ship = new Submarine();

        assertFalse(ship.isSunk());
        ship.shootAt(0, 0);

        assertTrue(ship.isSunk());
    }

    @Test
    void isDamaged() {
        Ship ship = new Destroyer();
        ship.setHorizontal(true);

        assertFalse(ship.isDamaged());
        ship.shootAt(0, 0);

        assertTrue(ship.isDamaged());

        ship.shootAt(0, 1);
        assertTrue(ship.isSunk());
        assertFalse(ship.isDamaged());
    }

    @Test
    void reset() {
        Ship ship = new Submarine();

        assertFalse(ship.isSunk());
        ship.shootAt(0, 0);

        assertTrue(ship.isSunk());
        ship.reset();
        assertFalse(ship.isSunk());
    }
}