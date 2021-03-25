package ru.sbt.mipt.oop.events;

public enum SensorEventType {
    LIGHT_ON, LIGHT_OFF, DOOR_OPEN, DOOR_CLOSED,
    ALARM_ACTIVATE, ALARM_DEACTIVATE;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code = "";


}
