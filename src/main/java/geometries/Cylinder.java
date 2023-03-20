package geometries;

import primitives.*;
import static primitives.Util.isZero;

/**
 * This class represents a Cylinder object
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class Cylinder extends Tube {
    private final double height;

    /**
     * Constructor to initialize Cylinder object with its parameters
     *
     * @param height the height of the Cylinder object
     * @param axisRay Ray object representing the axis of the tube
     * @param radius the radius of the Tube object
     * @throws IllegalArgumentException if the height is less or equals to 0
     */
    public Cylinder(double height, Ray axisRay, double radius) {
        super(axisRay, radius);

        if (height <= 0)
            throw new IllegalArgumentException("The height must be greater than 0");
        this.height = height;
    }

    /**
     * Get the height of the Cylinder object
     *
     * @return height of the Cylinder object
     */
    public double getHeight() {
        return this.height;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    @Override
    public String toString() {
        return String.format("Cylinder {Height = %s, %s}", this.height, super.toString());
    }
}