package ru.sbt.mipt.oop;

public class SensorCommandFactory implements CommandFactory {
    @Override
    public void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }
}
