package com.jack.driverlesscar;

import com.jack.driverlesscar.car.AutoCar;
import com.jack.driverlesscar.car.MyAutoCar;
import com.jack.driverlesscar.constant.Command;
import com.jack.driverlesscar.constant.Orientation;
import com.jack.driverlesscar.exception.DriveException;
import com.jack.driverlesscar.park.Park;
import com.jack.driverlesscar.park.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarTest {

    private AutoCar car;
    private Park park;
    private Position initPos;

    @Before
    public void before(){
        initPos = new Position(1, 1);
        park = new Park(4, 4);
    }

    @After
    public void after(){
        car.stopAuto();
    }

    @Test
    public void testTurn() throws DriveException {
        car = new MyAutoCar(park, initPos, Orientation.NORTH);
        car.move(Command.TURN_CLOCKWISE.name());
        assertEquals(1, car.getPositionX());
        assertEquals(1, car.getPositionY());
        assertEquals( Orientation.EAST.name(), car.getOrientation());
    }

    @Test
    public void testForward() throws DriveException {
        car = new MyAutoCar(park, initPos, Orientation.NORTH);
        car.move(Command.FORWARD.name());
        assertEquals(1, car.getPositionX());
        assertEquals(2, car.getPositionY());
        assertEquals(Orientation.NORTH.name(), car.getOrientation());
    }

    @Test
    public void testForwardEAST() throws DriveException {
        car = new MyAutoCar(park, initPos, Orientation.EAST);
        car.move(Command.FORWARD.name());
        assertEquals(2, car.getPositionX());
        assertEquals(1, car.getPositionY());
        assertEquals(Orientation.EAST.name(), car.getOrientation());
    }

    @Test(expected = DriveException.class)
    public void testOutOfPark() throws DriveException {
        car = new MyAutoCar(park, initPos, Orientation.WEST);
        car.move(Command.FORWARD.name());
    }

    @Test
    public void testForwardTwice() throws DriveException {
        car = new MyAutoCar(park, initPos, Orientation.EAST);
        car.move(Command.FORWARD.name());
        car.move(Command.FORWARD.name());
        assertEquals(3, car.getPositionX());
        assertEquals(1, car.getPositionY());
        assertEquals(Orientation.EAST.name(), car.getOrientation());
    }


}
