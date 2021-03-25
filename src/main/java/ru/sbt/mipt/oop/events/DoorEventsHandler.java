package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_OPEN;

public class DoorEventsHandler {

    private final SmartHome smartHome;
    private final SensorEvent event;

    public DoorEventsHandler( SensorEvent event, SmartHome smartHome) {
        this.smartHome = smartHome;
        this.event = event;
    }

    public void handle() {
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
                if (obj instanceof Room && event.getType() == DOOR_CLOSED) {

                    if (checkIfCloseHallDoor((Room) obj, event)) {
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        new HallDoorEventHandler((Room) obj, smartHome);
                    }
                }
            };

            smartHome.execute(action);
        }

    }

    private boolean checkIfCloseHallDoor(Room room, SensorEvent event) {

        for (Door door: room.getDoors()) {
            if (door.getId().equals(event.getObjectId())) {
                if (event.getType() == DOOR_CLOSED) {
                    return true;
                }
            }
        }
        return false;
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
