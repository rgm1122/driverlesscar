package com.jack.driverlesscar;

import com.jack.driverlesscar.car.AutoCar;
import com.jack.driverlesscar.car.MyAutoCar;
import com.jack.driverlesscar.constant.Command;
import com.jack.driverlesscar.constant.Orientation;
import com.jack.driverlesscar.park.Position;
import com.jack.driverlesscar.park.Park;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Position initPos = new Position(1, 1);
        Park park = new Park(4, 4);
        park.addBarrier(new Position(1, 3));
        park.addBarrier(new Position(2, 3));
        AutoCar car = new MyAutoCar(park, initPos, Orientation.NORTH);

        logger.info("Please send your command:\n" +
                "0: Exit\n" +
                "1: Forward\n" +
                "2: Turn\n" +
                "3: ChangeDriveMode\n" +
                "");
        Scanner scan = new Scanner(System.in);
        try {
            while (scan.hasNextInt()) {
                int c = scan.nextInt();
                switch (c) {
                    case 0:
                        doExit(car);
                        break;
                    case 1://forward
                        car.move(Command.FORWARD.name());
                        break;
                    case 2://turn
                        car.move(Command.TURN.name());
                        break;
                    case 3://changeMode
                        car.changeDriveMode();
                        break;
                    default:
                        logger.error("Unknown command");
                        break;
                }
            }
        }catch(Exception e){
            logger.error("Run command error", e);
            doExit(car);
        }
    }

    private static void doExit(AutoCar car) {
        car.stopAuto();
        logger.info("Bye");
        System.exit(0);
    }
}
