package ru.sbt.mipt.oop.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.events.LightEventsHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventType;
import ru.sbt.mipt.oop.smarthomereader.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class LightEventsHandlerTest {

    private static SmartHome smartHome;
    private static SensorEvent event1;
    private static SensorEvent event2;

    @BeforeClass
    public static void setUp() throws Exception {
        SmartHomeReader homeReader = new JsonSmartHomeReader( "smart-home-1.js");
        // считываем состояние дома из файла
        smartHome = homeReader.readSmartHomeData();
        SensorEventType sensorEventTypeOn = SensorEventType.values()[0];
        SensorEventType sensorEventTypeOff = SensorEventType.values()[1];
        event1 = new SensorEvent(sensorEventTypeOn, "5");
        event2 = new SensorEvent(sensorEventTypeOff, "2");

    }

    public static class LightFinder implements Action {

        private Light foundLight;
        private final String id;

        public LightFinder(SensorEvent event) {
            this.id = event.getObjectId();
        }

        @Override
        public void doAction(HomeComponent homeComponent) {
            if (homeComponent instanceof Light) {
                Light light = (Light) homeComponent;
                if (light.getId().equals(id)) {
                    this.foundLight = light;
                }
            }
        }

        public Light getFoundLight() {
            return foundLight;
        }
    }

    @Test
    public void lightEventHandlerTestOn() {
        new LightEventsHandler(smartHome).handle(event1);
        LightFinder lightFinder = new LightFinder(event1);
        smartHome.execute(lightFinder);
        if (lightFinder.getFoundLight() != null) {
            assertTrue(lightFinder.getFoundLight().isOn());
        }
    }

    @Test
    public void doorEventHandlerTestOff() {
        new LightEventsHandler(smartHome).handle(event2);
        LightFinder lightFinder = new LightFinder(event1);
        smartHome.execute(lightFinder);
        if (lightFinder.getFoundLight() != null) {
            assertFalse(lightFinder.getFoundLight().isOn());
        }
    }
}