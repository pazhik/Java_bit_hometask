package ru.sbt.mipt.oop;

public class HallDoorEvent {
    private final SmartHome smartHome;

    public HallDoorEvent(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void turnOffAllLight() {
        CommandFactory commandFactory = new SensorCommandFactory();
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                commandFactory.sendCommand(command);
            }
        }
    }

}
