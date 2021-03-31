package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_OPEN;

public class DoorEventsHandler implements Handler{

    private final SmartHome smartHome;

    public DoorEventsHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handle(SensorEvent event) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            Action action = (obj) -> {
                if (obj instanceof Door) {
                    Door door = (Door) obj;
                    if (door.getId().equals(event.getObjectId())) {
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
