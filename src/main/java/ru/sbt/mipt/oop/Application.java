package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHomeReader homeReader = new JsonSmartHomeReader( "smart-home-1.js");
        // считываем состояние дома из файла
        SmartHome smartHome = homeReader.readSmartHomeData();
        // начинаем цикл обработки событий
        new EventHandler(smartHome).eventHandling();
    }

}