package ru.sbt.mipt.oop;

public class EventHandler {

    protected final SmartHome smartHome;

    public EventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void eventHandling() {
        EventFactory eventCreator = new SensorEventFactory();
        SensorEvent event = eventCreator.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);

            new LightEventsHandler(event, smartHome);
            new DoorEventsHandler(event, smartHome);

            event = eventCreator.getNextSensorEvent();
        }
    }

}
