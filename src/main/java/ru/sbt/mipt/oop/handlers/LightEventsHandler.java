package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEvent;

import static ru.sbt.mipt.oop.events.EventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.events.EventType.LIGHT_ON;

public class LightEventsHandler implements Handler {

    private final SmartHome smartHome;

    public LightEventsHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handle(Event event) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            Action action = (obj) -> {
                if (obj instanceof Light) {
                    Light light = (Light) obj;
                    if (light.getId().equals(((SensorEvent) event).getObjectId())) {
                        if (event.getType() == LIGHT_ON) {
                            lightOn(light);
                        } else {
                            lightOff(light);
                        }
                    }
                }
            };

            smartHome.execute(action);
        }
    }

    private void lightOn(Light light) {
        light.setOn(true);
        System.out.println("Light " + light.getId() + " was turned on.");
    }

    private void lightOff(Light light) {
        light.setOn(false);
        System.out.println("Light " + light.getId() + " was turned off.");
    }
}
