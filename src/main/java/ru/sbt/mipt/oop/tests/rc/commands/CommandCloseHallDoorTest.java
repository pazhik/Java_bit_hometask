package ru.sbt.mipt.oop.tests.rc.commands;
import org.testng.annotations.*;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.rc.commands.Command;
import ru.sbt.mipt.oop.rc.commands.CommandCloseHallDoor;
import ru.sbt.mipt.oop.smarthomereader.JsonSmartHomeReader;

import static org.testng.AssertJUnit.*;

public class CommandCloseHallDoorTest {
    @Test
    public void CloseHallDoor() {
        SmartHome smartHome =  (new JsonSmartHomeReader( "smart-home-1.js")).readSmartHomeData();
        Command command = new CommandCloseHallDoor(smartHome);
        command.execute();
        smartHome.execute(homeComponent ->{
            if(!(homeComponent instanceof Room)){
                return;
            }

            Room room = (Room) homeComponent;
            if(room.getName().equals("hall")){
                room.execute(roomComponent ->{
                    if(!(roomComponent instanceof Door)){
                        return;
                    }
                    Door door = (Door) roomComponent;
                    assertFalse(door.isOpen());
                });
            }
        });
    }
}
