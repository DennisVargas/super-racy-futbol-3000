package com.dv.superracyfutbol3000;

import junit.framework.AssertionFailedError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveOrderTest {

    @Test
    void testCreateDefaultMoveOrderConstructor() {
        MoveOrder move_order = new MoveOrder();
        Assertions.assertNotNull(move_order);
    }

    @Test
    void testGetAccelOrderDefaultConst() {
        MoveOrder move_order = new MoveOrder();
        Assertions.assertEquals(MoveOrder.CarCommands.none, move_order.getAccelCommand());
        Assertions.assertEquals(MoveOrder.CarCommands.none, move_order.getTurnCommand());
    }

    @Test
    void testSetAccelOrderOverloadConstructor() {
        MoveOrder move_order = new MoveOrder(MoveOrder.CarCommands.accelerate, MoveOrder.CarCommands.straight);
        Assertions.assertEquals(MoveOrder.CarCommands.accelerate, move_order.getAccelCommand());
        Assertions.assertEquals(MoveOrder.CarCommands.straight, move_order.getTurnCommand());
    }
}