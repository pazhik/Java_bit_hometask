package ru.sbt.mipt.oop.tests.rc.commands;
import org.testng.annotations.*;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.rc.commands.Command;
import ru.sbt.mipt.oop.rc.commands.CommandTurnOnLightsInHall;
import ru.sbt.mipt.oop.smarthomereader.JsonSmartHomeReader;

import static org.testng.AssertJUnit.*;

public class CommandTurnOnHallLightTest {
    @Test
    public void turnOnLightsInHall() {
        SmartHome smartHome =  (new JsonSmartHomeReader( "smart-home-1.js")).readSmartHomeData();
        Command command = new CommandTurnOnLightsInHall(smartHome);
        command.execute();
        smartHome.execute(homeComponent ->{
            if(!(homeComponent instanceof Room)){
                return;
            }
            Room room = (Room) homeComponent;
            if(room.getName().equals("hall")) {
                room.execute(roomComponent->{
                    if (roomComponent instanceof Light) {
                        assertTrue(((Light) roomComponent).isOn());
                    }
                });
            }
        });
    }
}
