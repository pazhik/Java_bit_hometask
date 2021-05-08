package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.SmartHome;

public class CommandTurnOffAllLight implements Command{
    private final SmartHome smartHome;

    public CommandTurnOffAllLight(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(homeComponent ->{
            if(homeComponent instanceof Light){
                ((Light) homeComponent).setOn(false);
            }
        });
    }
}
