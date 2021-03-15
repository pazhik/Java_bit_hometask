package shapes;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SquareJunitTest {
    private Square sqr;
    private final double answer = 8.;

    @Before
    public void setUp(){
        ArrayList<Double> input_sqr = new ArrayList<Double>(Arrays.asList(1., 2., 3., 4.));
        sqr = new Square(input_sqr);
    }

    @Test
    public void area() {
        assertEquals(answer, sqr.area(), 0.00001);
    }
}