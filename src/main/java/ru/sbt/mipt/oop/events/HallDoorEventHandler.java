package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.commands.CommandFactory;
import ru.sbt.mipt.oop.commands.CommandSender;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements Handler {
    private final SmartHome smartHome;

    public HallDoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handle(SensorEvent event) {
        // Turn off all light
        if (event.getType() != DOOR_CLOSED) {
            return;
        }

        Action actionWithLight = (obj) -> {

            if (obj instanceof Light) {
                Light light = (Light) obj;
                CommandFactory commandFactory = new CommandSender();
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                commandFactory.sendCommand(command);
            }
        };

        Action checkDoorsAction = (obj) -> {
            if (obj instanceof Door) {
                Door door = (Door) obj;
                if (door.getId().equals(event.getObjectId())) {
                    smartHome.execute(actionWithLight);
                }
            }
        };

        Action checkHallRoomAction = (obj) -> {
            if (obj instanceof Room) {
                Room room = (Room) obj;
                if (room.getName().equals("hall")) {
                    room.execute(checkDoorsAction);
                }
            }
        };

        smartHome.execute(checkHallRoomAction);
    }
}
