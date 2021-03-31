package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;

import static ru.sbt.mipt.oop.events.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.EventType.DOOR_OPEN;

public class DoorEventsHandler implements Handler{

    private final SmartHome smartHome;

    public DoorEventsHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handle(Event event) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {

            Action action = (obj) -> {
                if (obj instanceof Door) {
                    Door door = (Door) obj;
                    if (door.getId().equals(((SensorEvent) event).getObjectId())) {
                        if (event.getType() == DOOR_OPEN) {
                            openDoor(door);
                        } else {
                            closeDoor(door);
                        }
                    }
                }
            };

            smartHome.execute(action);
        }

    }

    private void closeDoor(Door door) {
        door.setOpen(false);
        System.out.println("Door " + door.getId() +   " was closed.");
    }

    private void openDoor(Door door) {
        door.setOpen(true);
        System.out.println("Door " + door.getId()  + " was opened.");
    }
}
