package ru.sbt.mipt.oop.events;

public class SignalingEvent implements Event {
    private final EventType type;
    private final String code;

    public SignalingEvent(EventType type, String code) {
        this.type = type;
        this.code = code;
    }

    public EventType getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", code='" + code + '\'' +
                '}';
    }
}
