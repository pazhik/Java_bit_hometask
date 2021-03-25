package ru.sbt.mipt.oop;

public class Signaling {
    String activationCode = "";
    SignalingState signalingState;

    public Signaling(SignalingState state) {
        signalingState = state;
    }

    public void activate(String code) {
        activationCode = code;
        changeState(new ActivatedSignalingState());
    }

    public void deactivate(String code) {
        if (!code.equals(activationCode)) {
            activationCode = "";
            changeState(new DeactivatedSignalingState());
        } else {
            changeState(new AlarmSignalingState());
        }
    }

    public void activateAlarm() {
        changeState(new AlarmSignalingState());
    }

    private void changeState(SignalingState state) {
        signalingState = state;
    }
}
