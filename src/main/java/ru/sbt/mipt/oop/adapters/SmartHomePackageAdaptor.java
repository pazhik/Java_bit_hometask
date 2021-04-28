package ru.sbt.mipt.oop.adapters;

import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.handlers.Handler;
import smarthome.events.CCSensorEvent;
import smarthome.events.EventHandler;

import java.util.Map;

public class SmartHomePackageAdaptor implements EventHandler {
    private final Handler handler;
    private final Map<String, EventType> typeMatch;

    public SmartHomePackageAdaptor(Handler handler, Map<String, EventType> typeMatch) {
        this.handler = handler;
        this.typeMatch = typeMatch;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = adaptCCToSensorEvent(event);
        handler.handle(sensorEvent);
    }

    private SensorEvent adaptCCToSensorEvent(CCSensorEvent event) {
        return new SensorEvent(typeMatch.get(event.getEventType()), event.getObjectId());
    }
}
