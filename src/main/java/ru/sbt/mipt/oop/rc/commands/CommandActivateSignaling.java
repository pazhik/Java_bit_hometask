package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.signaling.Signaling;

public class CommandActivateSignaling implements Command{
    private final Signaling alarm;
    private final String code;
    public CommandActivateSignaling(Signaling alarm, String code) {
        this.alarm = alarm;
        this.code = code;
    }

    @Override
    public void execute() {
        alarm.activate(this.code);
    }
}
