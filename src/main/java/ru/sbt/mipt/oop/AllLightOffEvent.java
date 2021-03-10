package ru.sbt.mipt.oop;

public class AllLightOffEvent {
    private final SmartHome smartHome;

    public AllLightOffEvent(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void turnOffAllLight() {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                sendCommand(command);
            }
        }
    }

    private void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
