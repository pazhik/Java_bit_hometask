package shapes;

import java.util.ArrayList;
import java.util.Collections;

public class Circle implements Shape {
    // 0 - x, 1 - y
    private final ArrayList<Double> dots;
    private final double rad;

    public Circle (ArrayList<Double> input, double radius) {
        dots = new ArrayList<>(input);
        rad = radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(rad, 2);
    }
}
