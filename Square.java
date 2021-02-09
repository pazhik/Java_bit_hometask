import java.util.ArrayList;
import java.util.Collections;

public class Square implements Shape {
    private ArrayList<Double> dots;

    public Square (ArrayList<Double> input) {
        dots = new ArrayList<>(input);
    }

    @Override
    public double area() {
        Double side_length = Math.sqrt(Math.pow(dots.get(0) - dots.get(2), 2)
                                + Math.pow(dots.get(1) - dots.get(3), 2));
        return (side_length * side_length);
    }
}
