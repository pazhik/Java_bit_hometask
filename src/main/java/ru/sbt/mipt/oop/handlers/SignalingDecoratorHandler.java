package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SignalingEvent;
import ru.sbt.mipt.oop.signaling.Signaling;

public class SignalingDecoratorHandler implements Handler{
    private Handler handler;
    private Signaling signaling;

    public SignalingDecoratorHandler(Handler handler, Signaling signaling) {
        this.handler = handler;
        this.signaling = signaling;
    }

    @Override
    public void handle(Event event) {
        if(signaling.isDeactivated()) {
            handler.handle(event);
        } else if(signaling.isAlarm()) {
            System.out.println("Sending sms");
        } else {
            tryDeactivate(event);
        }
    }

    private void tryDeactivate(Event event) {
        if(event instanceof SignalingEvent) {
            handler.handle(event);
        } else {
            signaling.activateAlarm();
            System.out.println("Sending sms");
        }
    }
}
