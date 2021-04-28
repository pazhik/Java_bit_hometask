package ru.sbt.mipt.oop.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.handlers.DoorEventsHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.smarthomereader.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;

import static org.testng.AssertJUnit.*;

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
        EventType eventTypeClose = EventType.values()[3];
        EventType eventTypeOpen = EventType.values()[2];
        event1 = new SensorEvent(eventTypeClose, "3");
        event2 = new SensorEvent(eventTypeOpen, "4");
        event3 = new SensorEvent(eventTypeClose, "4");
    }

    public static class DoorFinder implements Action {

        private Door foundDoor;
        private final String id;

        public DoorFinder(SensorEvent event) {
            this.id = event.getObjectId();
        }

        @Override
        public void doAction(HomeComponent homeComponent) {
            if (homeComponent instanceof Door) {
                Door door = (Door) homeComponent;
                if (door.getId().equals(id)) {
                    this.foundDoor = door;
                }
            }
        }

        public Door getFoundDoor() {
            return foundDoor;
        }
    }

    @Test
    public void doorEventHandlerTestClose() {
        new DoorEventsHandler(smartHome).handle(event1);
        DoorFinder doorFinder = new DoorFinder(event1);
        smartHome.execute(doorFinder);
        if (doorFinder.getFoundDoor() != null) {
            assertFalse(doorFinder.getFoundDoor().isOpen());
        }
    }

    @Test
    public void doorEventHandlerTestOpen() {
        new DoorEventsHandler(smartHome).handle(event2);
        DoorFinder doorFinder = new DoorFinder(event2);
        smartHome.execute(doorFinder);
        if (doorFinder.getFoundDoor() != null) {
            assertTrue(doorFinder.foundDoor.isOpen());
        }
    }
}