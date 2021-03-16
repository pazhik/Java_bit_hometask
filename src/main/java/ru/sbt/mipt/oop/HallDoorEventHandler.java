package ru.sbt.mipt.oop;

public class HallDoorEventHandler {
    public HallDoorEventHandler(Room room, SmartHome smartHome) {
        if (room.getName().equals("hall")) {
            new HallDoorEvent(smartHome).turnOffAllLight();
        }
    }
}
