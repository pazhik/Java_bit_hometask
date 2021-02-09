import java.util.ArrayList;
import java.util.Collections;

public class Rectangle implements Shape {
    private ArrayList<Double> dots;

    public Rectangle (ArrayList<Double> input) {
        dots = new ArrayList<>(input);
    }

    @Override
    public double area() {
        Double side_length_first = Math.sqrt(Math.pow(dots.get(0) - dots.get(2), 2)
                + Math.pow(dots.get(1) - dots.get(3), 2));
        Double side_length_second = Math.sqrt(Math.pow(dots.get(2) - dots.get(4), 2)
                + Math.pow(dots.get(3) - dots.get(5), 2));
        return (side_length_first * side_length_second);
    }
}

