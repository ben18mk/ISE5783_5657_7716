package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;

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
     * Gets the Geo Point of this object
     *
     * @param ray ray
     * @param t distance from the ray start
     * @return geo point
     */
    private GeoPoint getGeoPoint(Ray ray, double t) {
        return new GeoPoint(this, ray.getPoint(t));
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
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
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

        double t1 = alignZero(tm - th);
        if (alignZero(t1 - maxDistance) > 0)
            return null;

        double t2 = alignZero(tm + th);
        if (t2 <= 0)
            return null;

        if (alignZero(t2 - maxDistance) >= 0)
            return t1 <= 0 ? null : List.of(getGeoPoint(ray, t1));
        return t1 <= 0 ? List.of(getGeoPoint(ray, t2)) : List.of(getGeoPoint(ray, t1), getGeoPoint(ray, t2));
    }

    @Override
    public String toString() {
        return String.format("Sphere {Center: %s, Radius: %s}", this.center, super.radius);
    }
}
