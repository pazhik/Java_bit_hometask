package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.*;

import java.util.List;

public class EventHandler {

    protected final SmartHome smartHome;
    private final EventFactory eventCreator;
    private final List<Handler> handlerList;

    public EventHandler(SmartHome smartHome, EventFactory eventCreator, List<Handler> handlers) {
        this.smartHome = smartHome;
        this.eventCreator = eventCreator;
        this.handlerList = handlers;
    }

    public void eventHandling() {
        SensorEvent event = eventCreator.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (Handler handler : handlerList) {
                handler.handle(event);
            }
            event = eventCreator.getNextSensorEvent();
        }
    }

}
