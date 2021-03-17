package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.commands.CommandFactory;
import ru.sbt.mipt.oop.commands.SensorCommand;

public class SensorCommandFactory implements CommandFactory {
    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
