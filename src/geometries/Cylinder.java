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
        Point bottomPoint = this.axisRay.getStartPoint();
        Vector dir = this.axisRay.getDirection();
        
        if (point.equals(bottomPoint)) // Check if the point is the bottom center point of the cylinder
            return dir.scale(-1); // Returns the vector, opposite to the axis direction vector

        // Get the projection created by the axis vector and the vector between the top center point and the point
        double projection = dir.dotProduct(point.subtract(bottomPoint.add(dir.scale(this.height)))); // TODO: Maybe add AlignZero()
        if (isZero(projection))
            return dir; // If the projection is 0, it means that the point is on the top part of the cylinder

        // Get the projection created by the axis vector and the vector between the bottom center point and the point
        projection = dir.dotProduct(point.subtract(bottomPoint)); // TODO: Maybe add AlignZero()
        if (isZero(projection))
            return dir.scale(-1); // If the projection is 0, it means that the point is on the top part of the cylinder

        // Otherwise, the point is on the side of the cylinder
        // NOTE: I use bottomPoint to save the orthogonal point on the ray and not create a new variable in order not to harm performance
        bottomPoint = bottomPoint.add(dir.scale(projection));
        return point.subtract(bottomPoint).normalize();
    }

    @Override
    public String toString() {
        return String.format("Cylinder {Height = %s, %s}", this.height, super.toString());
    }
}