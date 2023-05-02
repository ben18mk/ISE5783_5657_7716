package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
    public List<Point> findIntersections(Ray ray) {
        Point P0 = ray.getStartPoint();
        Vector v = ray.getDirection();

        double t;
        double nv = this.normal.dotProduct(v);

        if (isZero(nv))
            return null;

        try
        {
            t = alignZero(this.normal.dotProduct(this.q0.subtract(P0)) / nv);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }

        return t > 0 ? List.of(ray.getPoint(t)) : null;
    }

    @Override
    public String toString() {
        return String.format("Plane {Point: %s, Normal: %s}", this.q0, this.normal);
    }
}