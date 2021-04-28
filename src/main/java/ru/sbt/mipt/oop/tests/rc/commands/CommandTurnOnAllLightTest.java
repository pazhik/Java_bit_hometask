package ru.sbt.mipt.oop.tests.rc.commands;
import org.testng.annotations.*;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.rc.commands.Command;
import ru.sbt.mipt.oop.rc.commands.CommandTurnOnAllLight;
import ru.sbt.mipt.oop.smarthomereader.JsonSmartHomeReader;

import static org.testng.AssertJUnit.*;

public class CommandTurnOnAllLightTest {
    @Test
    public void turnOnALlLights() {
        SmartHome smartHome =  (new JsonSmartHomeReader( "smart-home-1.js")).readSmartHomeData();
        Command command = new CommandTurnOnAllLight(smartHome);
        command.execute();
        smartHome.execute(homeComponent ->{
            if(homeComponent instanceof Light){
                assertTrue(((Light) homeComponent).isOn());
            }
        });
    }
}
