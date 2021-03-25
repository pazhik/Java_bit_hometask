package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.events.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventHandler {
    SensorEvent sensorEvent;
    SmartHome smartHome;

    public AlarmEventHandler(SensorEvent event, SmartHome home) {
        sensorEvent = event;
        smartHome = home;
    }

    public void handle() {
        if (sensorEvent.getType() == ALARM_ACTIVATE) {
            smartHome.signaling.activate(sensorEvent.getType().getCode());
        } else if (sensorEvent.getType() ==  ALARM_DEACTIVATE){
            smartHome.signaling.deactivate(sensorEvent.getType().getCode());
        }
    }
}
