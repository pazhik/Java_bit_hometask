package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable, HomeComponent {
    Collection<Room> rooms;
    public Signaling signaling;

    public SmartHome() {
        signaling = new Signaling(new DeactivatedSignalingState());
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void execute(Action action) {
        action.doAction(this);
        rooms.forEach(room -> room.execute(action));
    }
}
