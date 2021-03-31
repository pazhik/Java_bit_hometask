package ru.sbt.mipt.oop.signaling;

public interface SignalingState {
    public void activate(String code);
    public void deactivate(String code);
    public void activateAlarm();
}
