package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commands.CommandFactory;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.SensorCommandFactory;

public class HallDoorEventUtils {
    private final SmartHome smartHome;

    public HallDoorEventUtils(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void turnOffAllLight() {

        Action action = (obj) -> {
            if (obj instanceof Light) {
                Light light = (Light) obj;
                CommandFactory commandFactory = new SensorCommandFactory();
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                commandFactory.sendCommand(command);
            }
        };

        smartHome.execute(action);
    }

}
