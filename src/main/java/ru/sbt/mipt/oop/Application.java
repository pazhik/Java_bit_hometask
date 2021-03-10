package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHome smartHome = new JsonSmartHomeReader( "smart-home-1.js").readSmartHomeData();
        // начинаем цикл обработки событий
        new EventHandler(smartHome).eventHandling();
    }

}