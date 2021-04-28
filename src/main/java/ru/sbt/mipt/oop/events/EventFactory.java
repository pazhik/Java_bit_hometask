package ru.sbt.mipt.oop.events;

public interface EventFactory {
    SensorEvent getNextSensorEvent();
}
