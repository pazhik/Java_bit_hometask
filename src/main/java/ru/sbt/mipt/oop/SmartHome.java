package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.signaling.DeactivatedSignalingState;
import ru.sbt.mipt.oop.signaling.Signaling;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable, HomeComponent {
    Collection<Room> rooms;
    public Signaling signaling;

    public SmartHome(String code) {
        signaling = new Signaling(code);
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms, String code) {
        this.rooms = rooms;
        signaling = new Signaling(code);
    }

    @Override
    public void execute(Action action) {
        action.doAction(this);
        rooms.forEach(room -> room.execute(action));
    }
}
