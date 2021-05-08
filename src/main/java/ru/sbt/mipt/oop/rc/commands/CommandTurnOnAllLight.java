package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;

public class CommandTurnOnAllLight implements Command{
    private final SmartHome smartHome;

    public CommandTurnOnAllLight(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeComponent ->{
            if(homeComponent instanceof Light){
                ((Light) homeComponent).setOn(true);
            }
        });
    }
}
