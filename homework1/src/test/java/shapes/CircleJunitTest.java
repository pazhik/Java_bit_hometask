package shapes;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class CircleJunitTest {
    private Circle circle;
    private final double answer = 9*Math.PI;

    @Before
    public void setUp() {
        ArrayList<Double> input_circle = new ArrayList<Double>(Arrays.asList(1., 2.));
        double input_radius = 3.;
        circle = new Circle(input_circle, input_radius);

    }

    @Test
    public void area() {
        assertEquals(answer, circle.area(),0.00001);
    }
}