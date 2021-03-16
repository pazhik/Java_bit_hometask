package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventsHandler {

    public LightEventsHandler(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            // событие от источника света
            new LightEvents(smartHome, event).handleEvent();
        }
    }
}
