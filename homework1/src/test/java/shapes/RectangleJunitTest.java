package shapes;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class RectangleJunitTest {
    private Rectangle rect;
    private final double answer = 16.;

    @Before
    public void setUp() {
        ArrayList<Double> input_rect = new ArrayList<Double>(Arrays.asList(1., 2., 3., 4., 7., 0.));
        rect = new Rectangle(input_rect);
    }

    @Test
    public void area() {
        assertEquals(answer, rect.area(),0.00001);
    }
}