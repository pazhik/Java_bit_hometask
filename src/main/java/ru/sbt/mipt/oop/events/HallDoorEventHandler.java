package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

public class HallDoorEventHandler {
    public HallDoorEventHandler(Room room, SmartHome smartHome) {
        if (room.getName().equals("hall")) {
            new HallDoorEventUtils(smartHome).turnOffAllLight();
        }
    }
}
