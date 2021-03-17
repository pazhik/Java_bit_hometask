package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEvents {
    private final SmartHome smartHome;
    private final SensorEvent event;

    public LightEvents(SmartHome smartHome, SensorEvent event) {
        this.smartHome = smartHome;
        this.event = event;
    }

    public void handleEvent() {

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

    private void lightOn(Light light) {
        light.setOn(true);
        System.out.println("Light " + light.getId() + " was turned on.");
    }

    private void lightOff(Light light) {
        light.setOn(false);
        System.out.println("Light " + light.getId() + " was turned off.");
    }

}
