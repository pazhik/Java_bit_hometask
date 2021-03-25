package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_ON;

public class LightEventsHandler {

    private final SmartHome smartHome;
    private final SensorEvent event;

    public LightEventsHandler(SensorEvent event, SmartHome smartHome) {
        this.smartHome = smartHome;
        this.event = event;
    }

    public void handle() {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            Action action = (obj) -> {
                if (obj instanceof Light) {
                    Light light = (Light) obj;
                    if (light.getId().equals(event.getObjectId())) {
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
