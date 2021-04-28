package ru.sbt.mipt.oop.signaling;

public class AlarmSignalingState implements SignalingState {
    private Signaling signaling;

    public AlarmSignalingState(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (signaling.isCorrectCode(code)) {
            signaling.changeState(new DeactivatedSignalingState(signaling));
        }
    }

    @Override
    public void activateAlarm() {
    }
}
