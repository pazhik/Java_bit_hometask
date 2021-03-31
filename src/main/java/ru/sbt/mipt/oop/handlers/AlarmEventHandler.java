package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SignalingEvent;
import ru.sbt.mipt.oop.signaling.Signaling;

import static ru.sbt.mipt.oop.events.EventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.events.EventType.ALARM_DEACTIVATE;

public class AlarmEventHandler implements Handler {
    Signaling signaling;

    public AlarmEventHandler(Signaling signaling) {
        this.signaling = signaling;
    }

    public void handle(Event event) {
        if (event.getType() == ALARM_ACTIVATE) {
            signaling.activate(((SignalingEvent) event).getCode());
        } else if (event.getType() == ALARM_DEACTIVATE){
            signaling.deactivate(((SignalingEvent) event).getCode());
        }
    }

}
