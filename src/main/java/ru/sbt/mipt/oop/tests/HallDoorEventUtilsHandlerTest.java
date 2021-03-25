package ru.sbt.mipt.oop.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.events.HallDoorEventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smarthomereader.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;

import java.util.Arrays;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class HallDoorEventUtilsHandlerTest {

    private static SmartHome smartHome;
    private static SensorEvent event1;
    private static Room hall;

    @BeforeClass
    public static void setUp() throws Exception {
        hall = new Room(Arrays.asList(new Light("7", true), new Light("8", true), new Light("9", true)),
                Arrays.asList(new Door(true, "4")),
                "hall");
        SmartHomeReader homeReader = new JsonSmartHomeReader( "smart-home-1.js");
        // считываем состояние дома из файла
        smartHome = homeReader.readSmartHomeData();
        SensorEventType sensorEventTypeClose = SensorEventType.values()[3];
        event1 = new SensorEvent(sensorEventTypeClose, "3");

    }

    @Test
    public void hallDoorEventHandlerTestCloseHallDoor() {
        new HallDoorEventHandler(hall, smartHome);
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                assertFalse(light.isOn());
            }
        }
    }


}