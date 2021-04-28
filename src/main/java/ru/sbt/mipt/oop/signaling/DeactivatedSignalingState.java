package ru.sbt.mipt.oop.signaling;

public class DeactivatedSignalingState implements SignalingState {
    private Signaling signaling;

    public DeactivatedSignalingState(Signaling signaling) {
        this.signaling = signaling;
    }

    @Override
    public void activate(String code) {
        if (signaling.isCorrectCode(code)) {
            signaling.changeState(new ActivatedSignalingState(signaling));
        }
    }

    @Override
    public void deactivate(String code) {
    }

    @Override
    public void activateAlarm() {
        signaling.changeState(new AlarmSignalingState(signaling));
    }
}
