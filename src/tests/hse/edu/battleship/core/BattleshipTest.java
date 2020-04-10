package hse.edu.battleship.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BattleshipTest {

    @Test
    void testToString() {
        Battleship ship = new Battleship();
        assertEquals("S", ship.toString());
    }

    @Test
    void getLength() {
        Battleship ship = new Battleship();
        assertEquals(4, ship.getLength());
    }

    @Test
    void getShipType() {
        Battleship ship = new Battleship();
        assertEquals("battleship", ship.getShipType());
    }
}