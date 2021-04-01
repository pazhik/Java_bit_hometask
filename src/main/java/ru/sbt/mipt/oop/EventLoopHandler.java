package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.handlers.Handler;

import java.util.List;

public class EventLoopHandler {

    protected final SmartHome smartHome;
    private final EventFactory eventCreator;
    private final List<Handler> handlerList;

    public EventLoopHandler(SmartHome smartHome, EventFactory eventCreator, List<Handler> handlers) {
        this.smartHome = smartHome;
        this.eventCreator = eventCreator;
        this.handlerList = handlers;
    }

    public void eventHandling() {
        Event event = eventCreator.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (Handler handler : handlerList) {
                handler.handle(event);
            }
            event = eventCreator.getNextSensorEvent();
        }
    }

}
