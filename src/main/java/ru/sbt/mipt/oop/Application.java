package ru.sbt.mipt.oop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import smarthome.events.SensorEventsManager;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SmartHomeConfiguration.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        sensorEventsManager.start();
//        SmartHomeReader homeReader = new JsonSmartHomeReader( "smart-home-1.js");
//        // считываем состояние дома из файла
//        SmartHome smartHome = homeReader.readSmartHomeData();
//        // начинаем цикл обработки событий
//        EventFactory eventCreator = new SensorEventFactory();
//        List<Handler> handlerList = new ArrayList<Handler>();
//        handlerList.add(new SignalingDecoratorHandler(new LightEventsHandler(smartHome), smartHome.signaling));
//        handlerList.add(new SignalingDecoratorHandler(new DoorEventsHandler(smartHome), smartHome.signaling));
//        handlerList.add(new SignalingDecoratorHandler(new HallDoorEventHandler(smartHome), smartHome.signaling));
//        handlerList.add(new SignalingDecoratorHandler(new AlarmEventHandler(smartHome.signaling), smartHome.signaling));
//        new EventHandler(smartHome, eventCreator, handlerList).eventHandling();
    }

}