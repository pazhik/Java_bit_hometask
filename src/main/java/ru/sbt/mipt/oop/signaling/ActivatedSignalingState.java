package ru.sbt.mipt.oop.signaling;


public class ActivatedSignalingState implements SignalingState {
    private Signaling signaling;

    public ActivatedSignalingState(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (signaling.isCorrectCode(code)) {
            signaling.changeState(new DeactivatedSignalingState(signaling));
        } else {
            activateAlarm();
        }

    }

    @Override
    public void activateAlarm() {
        signaling.changeState(new AlarmSignalingState(signaling));
    }
}
