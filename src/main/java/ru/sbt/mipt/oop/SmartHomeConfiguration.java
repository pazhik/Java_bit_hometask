package ru.sbt.mipt.oop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapters.SmartHomePackageAdaptor;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.smarthomereader.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;
import smarthome.events.SensorEventsManager;

import java.util.List;
import java.util.Map;

import static ru.sbt.mipt.oop.events.EventType.*;

@Configuration
public class SmartHomeConfiguration {
    @Bean
    SmartHomeReader createSmartHomeReader() {
        return new JsonSmartHomeReader( "smart-home-1.js");
    }

    @Bean
    SmartHome constructSmartHome() {
        return createSmartHomeReader().readSmartHomeData();
    }

    @Bean
    Handler alarmEventHandler(SmartHome smartHome) {
        return new AlarmEventHandler(smartHome.signaling);
    }

    @Bean
    Handler DoorEventHandler(SmartHome smartHome) {
        return new DoorEventsHandler(smartHome);
    }

    @Bean
    Handler hallDoorEventHandler(SmartHome smartHome) {
        return new HallDoorEventHandler(smartHome);
    }

    @Bean
    Handler lightEventHandler(SmartHome smartHome) {
        return new LightEventsHandler(smartHome);
    }

    @Bean
    SmartHomePackageAdaptor adapt(List<Handler> eventHandlers, SmartHome smartHome) {
        return new SmartHomePackageAdaptor(new SignalingDecoratorHandler(eventHandlers, smartHome.signaling), typeMap());
    }

    @Bean
    SensorEventsManager sensorEventsManagerAdaptor(SmartHomePackageAdaptor adapter) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(adapter);
        return sensorEventsManager;
    }

    @Bean
    Map<String, EventType> typeMap() {
        return Map.of(
                "LightIsOn", LIGHT_ON,
                "LightIsOff", LIGHT_OFF,
                "DoorIsOpen", DOOR_OPEN,
                "DoorIsUnlocked", DOOR_OPEN,
                "DoorIsClosed", DOOR_CLOSED,
                "DoorIsLocked", DOOR_CLOSED
        );
    }

}
