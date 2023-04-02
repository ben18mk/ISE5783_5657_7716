package geometries;

import primitives.*;

/**
 * This class represents a Plane object
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class Plane implements Geometry {
    private final Point q0;
    private final Vector normal;

    /**
     * Constructor to initialize Plane object based on 3 Point objects
     *
     * @param p1 Point object 1
     * @param p2 Point object 2
     * @param p3 Point object 3
     */
    public Plane(Point p1, Point p2, Point p3) {
        this.q0 = p1;
        this.normal = p1.subtract(p2).crossProduct(p1.subtract(p3)).normalize();
    }

    /**
     * Constructor to initialize Plane object based on a Point object and a normal Vector object
     *
     * @param q0 the Point object
     * @param normal the normal Vector object
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * Get the normal Vector object of the Plane object
     *
     * @return normal Vector object of the Plane object
     */
    public Vector getNormal() {
        return this.normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return this.normal;
    }

    @Override
    public String toString() {
        return String.format("Plane {Point: %s, Normal: %s}", this.q0, this.normal);
    }
}