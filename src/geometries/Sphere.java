package geometries;

import primitives.*;

/**
 * This class represents a Sphere object
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class Sphere extends RadialGeometry {
    private final Point center;

    /**
     * Constructor to initialize Sphere object with its parameters
     *
     * @param center Point object representing the center of the Sphere object
     * @param radius the radius of the Sphere object
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    /**
     * Get the center Point object of the Sphere object
     *
     * @return center Point object of the Sphere object
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Get the radius of the Sphere object
     *
     * @return radius of the Sphere object
     */
    public double getRadius() {
        return super.radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return point.subtract(this.center).normalize();
    }

    @Override
    public String toString() {
        return String.format("Sphere {Center: %s, Radius: %s}", this.center, super.radius);
    }
}
