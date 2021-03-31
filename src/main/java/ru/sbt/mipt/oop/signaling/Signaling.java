package ru.sbt.mipt.oop.signaling;

public class Signaling {
    String activationCode = "";
    SignalingState signalingState;

    public Signaling(String code) {
        signalingState = new DeactivatedSignalingState(this);
        activationCode = code;
    }

    public void activate(String code) {
        signalingState.activate(code);
    }

    public void deactivate(String code) {
        signalingState.deactivate(code);
    }

    public void activateAlarm() {
        signalingState.activateAlarm();
    }

    public void changeState(SignalingState state) {
        signalingState = state;
    }

    public SignalingState getState() {
        return signalingState;
    }

    public boolean isCorrectCode(String code) {
        return activationCode.equals(code);
    }

    public boolean isDeactivated() {
        return (signalingState instanceof DeactivatedSignalingState);
    }

    public boolean isAlarm() {
        return (signalingState instanceof AlarmSignalingState);
    }
}
