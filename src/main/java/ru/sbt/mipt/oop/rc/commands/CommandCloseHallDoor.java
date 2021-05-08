package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

public class CommandCloseHallDoor implements Command {
    private final SmartHome smartHome;

    public CommandCloseHallDoor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeComponent -> {
            if (!(homeComponent instanceof Room)) {
                return;
            }

            Room room = (Room) homeComponent;
            if (room.getName().equals("hall")) {
                room.execute(hallComponent -> {
                    if (hallComponent instanceof Door) {
                        Door door = (Door) hallComponent;
                        door.setOpen(false);
                    }
                });
            }

        });
    }
}
