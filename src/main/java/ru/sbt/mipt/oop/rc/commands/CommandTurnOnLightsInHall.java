package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;

public class CommandTurnOnLightsInHall implements Command{
    private final SmartHome smartHome;

    public CommandTurnOnLightsInHall(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeComponent ->{
            if(!(homeComponent instanceof Room)){
                return;
            }
            Room room = (Room) homeComponent;
            if(room.getName().equals("hall")) {
                room.execute(roomComponent->{
                    if (roomComponent instanceof Light) {
                        ((Light) roomComponent).setOn(true);
                    }
                });
            }
        });
    }
}
