package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.smarthomereader.JsonSmartHomeReader;
import ru.sbt.mipt.oop.smarthomereader.SmartHomeReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHomeReader homeReader = new JsonSmartHomeReader( "smart-home-1.js");
        // считываем состояние дома из файла
        SmartHome smartHome = homeReader.readSmartHomeData();
        // начинаем цикл обработки событий
        EventFactory eventCreator = new SensorEventFactory();
        List<Handler> handlerList = new ArrayList<Handler>();
        handlerList.add(new SignalingDecoratorHandler(new LightEventsHandler(smartHome), smartHome.signaling));
        handlerList.add(new SignalingDecoratorHandler(new DoorEventsHandler(smartHome), smartHome.signaling));
        handlerList.add(new SignalingDecoratorHandler(new HallDoorEventHandler(smartHome), smartHome.signaling));
        handlerList.add(new SignalingDecoratorHandler(new AlarmEventHandler(smartHome.signaling), smartHome.signaling));
        new EventHandler(smartHome, eventCreator, handlerList).eventHandling();
    }

}