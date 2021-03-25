package ru.sbt.mipt.oop.events;

public class SensorEventFactory implements EventFactory {
    //    New event types
    @Override
    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
//        if (sensorEventType == SensorEventType.ALARM_ACTIVATE || sensorEventType == SensorEventType.ALARM_DEACTIVATE) {
//            sensorEventType.setCode("alarm");
//        }
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
