import java.util.ArrayList;
import java.util.Collections;

public class Circle implements Shape {
    // 0 - x, 1 - y
    private ArrayList<Double> dots;
    private Double rad;

    public Circle (ArrayList<Double> input, Double radius) {
        dots = new ArrayList<>(input);
        rad = radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(rad, 2);
    }
}
