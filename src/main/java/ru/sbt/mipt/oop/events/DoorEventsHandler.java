package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.events.DoorEvents;
import ru.sbt.mipt.oop.events.SensorEvent;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_OPEN;

public class DoorEventsHandler {
    public DoorEventsHandler(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            new DoorEvents(smartHome, event).handleEvent();
        }
    }
}
