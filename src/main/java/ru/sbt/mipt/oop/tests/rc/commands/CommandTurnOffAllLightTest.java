package ru.sbt.mipt.oop.tests.rc.commands;
import org.testng.annotations.*;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.rc.commands.Command;
import ru.sbt.mipt.oop.rc.commands.CommandTurnOffAllLight;
import ru.sbt.mipt.oop.smarthomereader.JsonSmartHomeReader;

import static org.testng.AssertJUnit.*;

public class CommandTurnOffAllLightTest {
    @Test
    public void turnOffAllLights() {
        SmartHome smartHome =  (new JsonSmartHomeReader( "smart-home-1.js")).readSmartHomeData();
        Command command = new CommandTurnOffAllLight(smartHome);
        command.execute();
        smartHome.execute(homeComponent ->{
            if(homeComponent instanceof Light){
                assertFalse(((Light) homeComponent).isOn());
            }
        });
    }
}
