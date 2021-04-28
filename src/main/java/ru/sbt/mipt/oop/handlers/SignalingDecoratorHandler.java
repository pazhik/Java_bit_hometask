package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SignalingEvent;
import ru.sbt.mipt.oop.signaling.Signaling;

import java.util.List;

public class SignalingDecoratorHandler implements Handler{
    private List<Handler> handlers;
    private Signaling signaling;

    public SignalingDecoratorHandler(List<Handler> handlers, Signaling signaling) {
        this.handlers = handlers;
        this.signaling = signaling;
    }

    @Override
    public void handle(Event event) {
        if(signaling.isDeactivated()) {
            for (Handler handler: handlers) {
                handler.handle(event);
            }
        } else if(signaling.isAlarm()) {
            System.out.println("Sending sms");
        } else {
            tryDeactivate(event);
        }
    }

    private void tryDeactivate(Event event) {
        if(event instanceof SignalingEvent) {
            for (Handler handler: handlers) {
                handler.handle(event);
            }
        } else {
            signaling.activateAlarm();
            System.out.println("Sending sms");
        }
    }
}
