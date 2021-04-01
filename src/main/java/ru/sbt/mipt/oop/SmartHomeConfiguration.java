package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapters.SmartHomePackageAdaptor;
import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.smarthomereader.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;
import smarthome.events.SensorEventsManager;

import java.util.ArrayList;
import java.util.List;

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
    List<Handler> constuctEventHandlers() {
        SmartHome smartHome = constructSmartHome();
        List<Handler> handlerList = new ArrayList<Handler>();
        handlerList.add(new SignalingDecoratorHandler(new LightEventsHandler(smartHome), smartHome.signaling));
        handlerList.add(new SignalingDecoratorHandler(new DoorEventsHandler(smartHome), smartHome.signaling));
        handlerList.add(new SignalingDecoratorHandler(new HallDoorEventHandler(smartHome), smartHome.signaling));
        handlerList.add(new SignalingDecoratorHandler(new AlarmEventHandler(smartHome.signaling), smartHome.signaling));
        return handlerList;
    }

    @Bean
    SmartHomePackageAdaptor adapt() {
        return new SmartHomePackageAdaptor(constuctEventHandlers());
    }

    @Bean
    SensorEventsManager sensorEventsManagerAdaptor() {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(adapt());
        return sensorEventsManager;
    }

}
