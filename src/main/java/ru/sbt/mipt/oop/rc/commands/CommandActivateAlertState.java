package ru.sbt.mipt.oop.rc.commands;

import ru.sbt.mipt.oop.signaling.Signaling;

public class CommandActivateAlertState implements Command{
    private final Signaling alarm;
    public CommandActivateAlertState(Signaling alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.activateAlarm();
    }
}
