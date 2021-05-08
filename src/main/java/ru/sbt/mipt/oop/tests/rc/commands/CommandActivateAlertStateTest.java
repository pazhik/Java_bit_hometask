package ru.sbt.mipt.oop.tests.rc.commands;
import org.testng.annotations.*;
import ru.sbt.mipt.oop.rc.commands.Command;
import ru.sbt.mipt.oop.rc.commands.CommandActivateAlertState;
import ru.sbt.mipt.oop.signaling.Signaling;
import static org.testng.AssertJUnit.*;

public class CommandActivateAlertStateTest {
    @Test
    public void execute() {
        Signaling alarm = new Signaling("code");
        Command command = new CommandActivateAlertState(alarm);
        command.execute();
        assertTrue(alarm.isAlarm());
    }
}
