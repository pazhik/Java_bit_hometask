package ru.sbt.mipt.oop.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.events.HallDoorEventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smarthomereader.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class HallDoorEventUtilsHandlerTest {

    private static SmartHome smartHome;
    private static SensorEvent event1;
    private static Room hall;

    @BeforeClass
    public static void setUp() throws Exception {
        Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door(false, "1")),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(new Light("3", true)),
                Arrays.asList(new Door(false, "2")),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Arrays.asList(new Door(true, "3")),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)),
                Arrays.asList(new Door(true, "4")),
                "hall");
        smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
        // считываем состояние дома из файла
        event1 = new SensorEvent(SensorEventType.DOOR_CLOSED, "4");

    }

    public static class LightFinder implements Action {

        private Collection<Light> foundLight = new ArrayList<>();
        private final String id;

        public LightFinder(SensorEvent event) {
            this.id = event.getObjectId();
        }

        @Override
        public void doAction(HomeComponent homeComponent) {
            if (homeComponent instanceof Light) {
                Light light = (Light) homeComponent;
                this.foundLight.add(light);
            }
        }

        public Collection<Light> getFoundLight() {
            return foundLight;
        }
    }

    @Test
    public void hallDoorEventHandlerTestCloseHallDoor() {
        new HallDoorEventHandler(smartHome).handle(event1);
        LightFinder lightFinder = new LightFinder(event1);
        smartHome.execute(lightFinder);
        if (!lightFinder.getFoundLight().isEmpty()) {
            ArrayList<Light> foundedLights = new ArrayList<Light>(lightFinder.getFoundLight());
            for (Light foundedLight : foundedLights) {
                assertFalse(foundedLight.isOn());
            }
        }
//        for (Room homeRoom : smartHome.getRooms()) {
//            for (Light light : homeRoom.getLights()) {
//                assertFalse(light.isOn());
//            }
//        }
    }


}