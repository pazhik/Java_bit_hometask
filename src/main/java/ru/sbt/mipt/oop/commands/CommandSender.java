package ru.sbt.mipt.oop.commands;

public class CommandSender implements CommandFactory {
    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
