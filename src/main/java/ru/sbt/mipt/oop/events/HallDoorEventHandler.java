package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commands.CommandFactory;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;

public class HallDoorEventHandler {
    private final SmartHome smartHome;
    private final Room room;

    public HallDoorEventHandler(Room room, SmartHome smartHome) {
        this.smartHome = smartHome;
        this.room = room;
    }

    public void handle() {
        // Turn off all light
        if (room.getName().equals("hall")) {
            Action action = (obj) -> {
                if (obj instanceof Light) {
                    Light light = (Light) obj;
                    CommandFactory commandFactory = new CommandSender();
                    light.setOn(false);
                    SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                    commandFactory.sendCommand(command);
                }
            };

            smartHome.execute(action);
        }
    }
}
