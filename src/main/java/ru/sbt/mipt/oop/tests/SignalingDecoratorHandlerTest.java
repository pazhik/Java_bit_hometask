package ru.sbt.mipt.oop.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.EventType;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SignalingEvent;
import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.signaling.ActivatedSignalingState;
import ru.sbt.mipt.oop.signaling.AlarmSignalingState;
import ru.sbt.mipt.oop.signaling.DeactivatedSignalingState;
import ru.sbt.mipt.oop.signaling.Signaling;

import java.util.*;

import static org.testng.AssertJUnit.assertTrue;
public class SignalingDecoratorHandlerTest {
    static private SmartHome smartHome;
    static Queue<Event> events;

    @BeforeClass
    public static void setUp() throws Exception {
        Room kitchen = new Room(Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door(false, "1")),
                "kitchen");
        Room bathroom = new Room(Arrays.asList(new Light("3", true)),
                Arrays.asList(new Door(false, "2")),
                "bathroom");
        Room bedroom = new Room(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Arrays.asList(new Door(true, "3")),
                "bedroom");
        Room hall = new Room(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)),
                Arrays.asList(new Door(true, "4")),
                "hall");
        smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall), "123");
        events = new LinkedList<>();
        events.add(new SignalingEvent(EventType.ALARM_ACTIVATE, "123"));
        events.add(new SensorEvent(EventType.DOOR_CLOSED, "4"));
    }

    @Test
    public void AlarmStateAndIgnoringEventsTest(){
        List<Handler> handlerList = new ArrayList<Handler>();
        handlerList.add(new SignalingDecoratorHandler(new LightEventsHandler(smartHome), smartHome.signaling));
        handlerList.add(new SignalingDecoratorHandler(new DoorEventsHandler(smartHome), smartHome.signaling));
        handlerList.add(new SignalingDecoratorHandler(new HallDoorEventHandler(smartHome), smartHome.signaling));
        handlerList.add(new SignalingDecoratorHandler(new AlarmEventHandler(smartHome.signaling), smartHome.signaling));

        Event event;
        while (events.peek() != null) {
            event = events.poll();
            for (Handler handler : handlerList) {
                handler.handle(event);
            }

        }

        assertTrue(smartHome.signaling.isAlarm());
    }
}
