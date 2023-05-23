package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point P0 = ray.getStartPoint();
        Vector v = ray.getDirection();

        Vector u;

        try
        {
            u = this.center.subtract(P0);
        }
        catch (IllegalArgumentException e)
        {
            return List.of(new GeoPoint(this, ray.getPoint(this.radius)));
        }

        double tm = v.dotProduct(u);
        double d = Math.sqrt(u.lengthSquared() - tm * tm);

        if (d >= this.radius)
            return null;

        double th = Math.sqrt(this.radius * this.radius - d * d);

        List<GeoPoint> result = new ArrayList<>();
        if (alignZero(tm + th) > 0)
            result.add(new GeoPoint(this, ray.getPoint(alignZero(tm + th))));
        if (alignZero(tm - th) > 0)
            result.add(new GeoPoint(this, ray.getPoint(alignZero(tm - th))));

        if (isZero(result.size()))
            return null;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Sphere {Center: %s, Radius: %s}", this.center, super.radius);
    }
}
