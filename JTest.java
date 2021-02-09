import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class JTest {
    private Square sqr;
    private Rectangle rect;
    private Circle circle;
    private double[] Answers;

    @Before
    public void setUp() throws Exception {
        ArrayList<Double> input_sqr = new ArrayList<Double>(Arrays.asList(1., 2., 3., 4.));
        ArrayList<Double> input_rect = new ArrayList<Double>(Arrays.asList(1., 2., 3., 4., 7., 0.));
        ArrayList<Double> input_circle = new ArrayList<Double>(Arrays.asList(1., 2.));
        Double input_radius = 3.;
        sqr = new Square(input_sqr);
        rect = new Rectangle(input_rect);
        circle = new Circle(input_circle, input_radius);
        //answers: 0 - square, 1 - rectangle, 2 - circle
        Answers = new double[]{8., 16., 9*Math.PI};
    }

    @Test
    public void area() {
        assertEquals(Answers[0], sqr.area(), 0.00001);
        assertEquals(Answers[1], rect.area(),0.00001);
        assertEquals(Answers[2], circle.area(),0.00001);
    }
}