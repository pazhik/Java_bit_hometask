package ru.sbt.mipt.oop.tests.rc.commands;
import org.testng.annotations.*;
import ru.sbt.mipt.oop.rc.commands.Command;
import ru.sbt.mipt.oop.rc.commands.CommandActivateSignaling;
import ru.sbt.mipt.oop.signaling.Signaling;
import static org.testng.AssertJUnit.*;

public class CommandActivateSignalingTest {
    @Test
    public void execute() {
        Signaling alarm = new Signaling("code");
        Command command = new CommandActivateSignaling(alarm, "code");
        command.execute();
        assertTrue(alarm.isActive());
    }
}
