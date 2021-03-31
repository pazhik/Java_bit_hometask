package ru.sbt.mipt.oop.tests;

import org.testng.annotations.Test;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SignalingEvent;
import ru.sbt.mipt.oop.handlers.AlarmEventHandler;
import ru.sbt.mipt.oop.handlers.Handler;
import ru.sbt.mipt.oop.signaling.ActivatedSignalingState;
import ru.sbt.mipt.oop.signaling.AlarmSignalingState;
import ru.sbt.mipt.oop.signaling.DeactivatedSignalingState;
import ru.sbt.mipt.oop.signaling.Signaling;

import static org.testng.AssertJUnit.assertTrue;
public class SignalingTest {

    @Test
    public void signalingActivationStateWithCorrectCode() {
        Signaling signaling = new Signaling("123");
        Handler handler = new AlarmEventHandler(signaling);
        Event event = new SignalingEvent(EventType.ALARM_ACTIVATE, "123");

        handler.handle(event);
        assertTrue(signaling.getState() instanceof ActivatedSignalingState);
    }

    @Test
    public void signalingActivationStateWithIncorrectCode() {
        Signaling signaling = new Signaling("123");
        Handler handler = new AlarmEventHandler(signaling);
        Event event = new SignalingEvent(EventType.ALARM_ACTIVATE, "122");

        handler.handle(event);
        assertTrue(signaling.getState() instanceof DeactivatedSignalingState);
    }

    @Test
    public void signalingDeactivationStateWithCorrectCode() {
        Signaling signaling = new Signaling("123");
        signaling.changeState(new ActivatedSignalingState(signaling));
        Handler handler = new AlarmEventHandler(signaling);
        Event event = new SignalingEvent(EventType.ALARM_DEACTIVATE, "123");

        handler.handle(event);
        assertTrue(signaling.getState() instanceof DeactivatedSignalingState);
    }

    @Test
    public void signalingDeactivationStateWithIncorrectCode() {
        Signaling signaling = new Signaling("123");
        signaling.changeState(new ActivatedSignalingState(signaling));
        Handler handler = new AlarmEventHandler(signaling);
        Event event = new SignalingEvent(EventType.ALARM_DEACTIVATE, "122");

        handler.handle(event);
        assertTrue(signaling.getState() instanceof AlarmSignalingState);
    }
}