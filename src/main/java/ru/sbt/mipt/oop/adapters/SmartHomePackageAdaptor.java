package ru.sbt.mipt.oop.adapters;

import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.handlers.Handler;
import smarthome.events.CCSensorEvent;
import smarthome.events.EventHandler;

import java.util.List;

public class SmartHomePackageAdaptor implements EventHandler {
    private final List<Handler> handlerList;

    public SmartHomePackageAdaptor(List<Handler> handlerList) {
        this.handlerList = handlerList;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = adaptCCToSensorEvent(event);
        for (Handler handler : handlerList) {
            handler.handle(sensorEvent);
        }
    }

    private SensorEvent adaptCCToSensorEvent(CCSensorEvent event) {
        return new SensorEvent(adaptCCSensorEventType(event.getEventType()), event.getObjectId());
    }

    private EventType adaptCCSensorEventType(String eventType) {
        EventType adaptedEventType;
        switch (eventType) {
            case ("DoorIsOpen"):
            case ("DoorIsUnlocked"):
                adaptedEventType = EventType.DOOR_OPEN;
                break;
            case ("DoorIsClosed"):
            case ("DoorIsLocked"):
                adaptedEventType = EventType.DOOR_CLOSED;
                break;
            case ("LightIsOn"):
                adaptedEventType = EventType.LIGHT_ON;
                break;
            case ("LightIsOff"):
                adaptedEventType = EventType.LIGHT_OFF;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + eventType);
        }
        return adaptedEventType;
    }
}
