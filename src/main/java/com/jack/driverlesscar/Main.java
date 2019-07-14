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

    /**
     * 车辆模拟程序
     * 当手工模式时，可以向前和转向（顺时针和逆时针），前进时遇到异常则终止程序
     * 当自动模式时，则会按照设定模式前进，遇到停车场边界或者障碍时自动转向，自动模式时不能控制使用forward和turn命令
     * @param args
     */
    public static void main(String[] args) {
        Position initPos = new Position(1, 1);
        Park park = new Park(4, 4);
        park.addBarrier(new Position(1, 3));
        park.addBarrier(new Position(2, 3));
        AutoCar car = new MyAutoCar(park, initPos, Orientation.NORTH);

        logger.info("Please send your command:\n" +
                "0: Exit\n" +
                "1: Forward\n" +
                "2: Turn clockwise\n" +
                "3: Turn anticlockwise\n" +
                "4: ChangeDriveMode\n" +
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
                    case 2://turn clockwise
                        car.move(Command.TURN_CLOCKWISE.name());
                        break;
                    case 3://turn anticlockwise
                        car.move(Command.TURN_ANTICLOCKWISE.name());
                        break;
                    case 4://changeMode
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
