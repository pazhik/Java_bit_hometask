package ru.sbt.mipt.oop.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.events.DoorEventsHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smarthomereader.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class DoorUtilsHandlerTest {
    private static SmartHome smartHome;
    private static SensorEvent event1;
    private static SensorEvent event2;
    private static SensorEvent event3;

    @BeforeClass
    public static void setUp() throws Exception {
        SmartHomeReader homeReader = new JsonSmartHomeReader( "smart-home-1.js");
        // считываем состояние дома из файла
        smartHome = homeReader.readSmartHomeData();
        SensorEventType sensorEventTypeClose = SensorEventType.values()[3];
        SensorEventType sensorEventTypeOpen = SensorEventType.values()[2];
        event1 = new SensorEvent(sensorEventTypeClose, "3");
        event2 = new SensorEvent(sensorEventTypeOpen, "4");
        event3 = new SensorEvent(sensorEventTypeClose, "4");
    }

    @Test
    public void doorEventHandlerTestClose() {
        new DoorEventsHandler(event1, smartHome).handle();
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event1.getObjectId())) {
                    assertFalse(door.isOpen());
                }
            }
        }
    }

    @Test
    public void doorEventHandlerTestOpen() {
        new DoorEventsHandler(event2, smartHome).handle();
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event2.getObjectId())) {
                    assertTrue(door.isOpen());
                }
            }
        }
    }

    @Test
    public void doorEventHandlerTestCloseHallDoor() {
        new DoorEventsHandler(event3, smartHome);
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event3.getObjectId())) {
                    assertFalse(door.isOpen());
                }
            }
            if (room.getName().equals("hall")) {
                for (Light light: room.getLights()) {
                    assertFalse(light.isOn());
                }
            }
        }
    }
}