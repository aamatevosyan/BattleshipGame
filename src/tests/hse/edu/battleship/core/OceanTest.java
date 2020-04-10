package hse.edu.battleship.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OceanTest {

    @Test
    void placeAllShipsRandomly() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();

        ocean.printDebug();
    }

    @Test
    void removeShip() {
        Ocean ocean = new Ocean();
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        assertTrue(ship.okToPlaceShipAt(7, 6, true, ocean));
        ship.placeShipAt(7, 6, true, ocean);

        assertEquals("battleship", ocean.getAt(7, 6).getShipType());

        ocean.removeShip(7, 6, true, 4);
        assertEquals("empty", ocean.getAt(7, 6).getShipType());
    }

    @Test
    void forceSunkShip() {
        Ocean ocean = new Ocean();
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        assertTrue(ship.okToPlaceShipAt(7, 6, true, ocean));
        ship.placeShipAt(7, 6, true, ocean);

        assertEquals("battleship", ocean.getAt(7, 6).getShipType());
        assertFalse(ship.isSunk());

        ocean.forceSunkShip(7, 6, true, 4);
        assertTrue(ship.isSunk());
    }

    @Test
    void isOccupied() {
        Ocean ocean = new Ocean();
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        assertTrue(ship.okToPlaceShipAt(7, 6, true, ocean));
        ship.placeShipAt(7, 6, true, ocean);

        assertEquals("battleship", ocean.getAt(7, 6).getShipType());

        assertFalse(ocean.isOccupied(0, 0));
        assertTrue(ocean.isOccupied(7, 6));
    }

    @Test
    void shootAt() {
        Ocean ocean = new Ocean();
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        assertTrue(ship.okToPlaceShipAt(7, 6, true, ocean));
        ship.placeShipAt(7, 6, true, ocean);

        assertEquals("battleship", ocean.getAt(7, 6).getShipType());
        assertFalse(ship.isDamaged());

        ocean.shootAt(7, 6);
        assertTrue(ship.isDamaged());
    }

    @Test
    void getShotsFired() {
        Ocean ocean = new Ocean();
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        assertTrue(ship.okToPlaceShipAt(7, 6, true, ocean));
        ship.placeShipAt(7, 6, true, ocean);

        assertEquals("battleship", ocean.getAt(7, 6).getShipType());
        assertFalse(ship.isDamaged());
        assertEquals(0, ocean.getShotsFired());

        ocean.shootAt(7, 6);
        assertTrue(ship.isDamaged());
        assertEquals(1, ocean.getShotsFired());
    }

    @Test
    void getHitCount() {
        Ocean ocean = new Ocean();
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        assertTrue(ship.okToPlaceShipAt(7, 6, true, ocean));
        ship.placeShipAt(7, 6, true, ocean);

        assertEquals("battleship", ocean.getAt(7, 6).getShipType());
        assertFalse(ship.isDamaged());
        assertEquals(0, ocean.getShotsFired());
        assertEquals(0, ocean.getHitCount());

        ocean.shootAt(7, 6);
        assertTrue(ship.isDamaged());

        ocean.shootAt(0, 0);

        assertEquals(2, ocean.getShotsFired());
        assertEquals(1, ocean.getHitCount());
    }

    @Test
    void getShipsSunk() {
        Ocean ocean = new Ocean();
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        assertTrue(ship.okToPlaceShipAt(7, 6, true, ocean));
        ship.placeShipAt(7, 6, true, ocean);

        assertEquals("battleship", ocean.getAt(7, 6).getShipType());
        assertFalse(ship.isSunk());
        assertEquals(0, ocean.getShipsSunk());

        ocean.forceSunkShip(7, 6, true, 4);
        assertTrue(ship.isSunk());
        assertEquals(1, ocean.getShipsSunk());
    }

    @Test
    void print() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        ocean.print();
    }

    @Test
    void printDebug() {
        Ocean ocean = new Ocean();
        ocean.placeAllShipsRandomly();
        ocean.printDebug();
    }

    @Test
    void checkBound() {
        Ocean ocean = new Ocean();

        assertTrue(ocean.checkBound(0, 0));
        assertTrue(ocean.checkBound(9, 9));

        assertFalse(ocean.checkBound(10, 10));
    }

    @Test
    void testCheckBound() {
        Ocean ocean = new Ocean();

        assertTrue(ocean.checkBound(0));
        assertTrue(ocean.checkBound(9));

        assertFalse(ocean.checkBound(10));
    }

    @Test
    void setAt() {
        Ocean ocean = new Ocean();
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        assertTrue(ship.okToPlaceShipAt(7, 6, true, ocean));
        ocean.setAt(7, 6, ship);

        assertEquals("battleship", ocean.getAt(7, 6).getShipType());

        assertFalse(ocean.isOccupied(7, 7));
        assertTrue(ocean.isOccupied(7, 6));
    }

    @Test
    void getAt() {
        Ocean ocean = new Ocean();
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        assertTrue(ship.okToPlaceShipAt(7, 6, true, ocean));
        ocean.setAt(7, 6, ship);

        assertEquals("battleship", ocean.getAt(7, 6).getShipType());

        assertFalse(ocean.isOccupied(7, 7));
        assertTrue(ocean.isOccupied(7, 6));
    }

    @Test
    void getShipsDamaged() {
        Ocean ocean = new Ocean();
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        assertTrue(ship.okToPlaceShipAt(7, 6, true, ocean));
        ship.placeShipAt(7, 6, true, ocean);

        assertEquals(0, ocean.getShipsDamaged());

        assertEquals("battleship", ocean.getAt(7, 6).getShipType());
        assertFalse(ship.isDamaged());

        ocean.shootAt(7, 6);
        assertTrue(ship.isDamaged());
        assertEquals(1, ocean.getShipsDamaged());
    }

    @Test
    void getShipsAlive() {
        Ocean ocean = new Ocean();
        Ship ship = new Battleship();
        ship.setHorizontal(true);

        assertTrue(ship.okToPlaceShipAt(7, 6, true, ocean));
        ship.placeShipAt(7, 6, true, ocean);

        assertEquals("battleship", ocean.getAt(7, 6).getShipType());
        assertFalse(ship.isSunk());
        assertEquals(0, ocean.getShipsSunk());
        assertEquals(10, ocean.getShipsAlive());

        ocean.forceSunkShip(7, 6, true, 4);
        assertTrue(ship.isSunk());
        assertEquals(1, ocean.getShipsSunk());
        assertEquals(9, ocean.getShipsAlive());
    }

    @Test
    void getStats() {
        Ocean ocean = new Ocean();
        assertEquals("Total shoots: 0\n" +
                "Alive: 10\n" +
                "Damaged: 0\n" +
                "Sunk: 0\n", ocean.getStats().replace("\r\n", "\n"));
    }
}