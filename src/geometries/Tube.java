package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;
import static primitives.Util.alignZero;

/**
 * This class is the base for all classes using tubes
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class Tube extends RadialGeometry {
    protected final Ray axisRay;

    /**
     * Constructor to initialize Tube object with its parameters
     *
     * @param axisRay Ray object representing the axis of the tube
     * @param radius the radius of the Tube object
     */
    public Tube(Ray axisRay, double radius) {
        super(radius);
        this.axisRay = axisRay;
    }

    /**
     * Get the axis Ray object of the Tube object
     *
     * @return axis Ray object of the Tube object
     */
    public Ray getAxisRay() {
        return this.axisRay;
    }

    @Override
    public Vector getNormal(Point point) {
        Point rayPoint = this.axisRay.getStartPoint();
        Vector dir = this.axisRay.getDirection();
        double projection = alignZero(dir.dotProduct(point.subtract(rayPoint)));

        rayPoint = isZero(projection) ? rayPoint : rayPoint.add(dir.scale(projection));
        return point.subtract(rayPoint).normalize();
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point rayStart = ray.getStartPoint();
        Point tubeStart = this.axisRay.getStartPoint();
        Vector rayDir = ray.getDirection();
        Vector tubeDir = this.axisRay.getDirection();
        Vector k;

        try {
            k = rayDir.crossProduct(tubeDir);
        }
        catch (IllegalArgumentException ex) {
            return null;
        }

        Vector e;
        try {
            e = rayStart.subtract(tubeStart).crossProduct(tubeDir);
        }
        catch (IllegalArgumentException ex) {
            List<GeoPoint> intersections = new LinkedList<>();
            intersections.add(new GeoPoint(this, rayStart.add(rayDir.scale(this.radius / k.length()))));
            return intersections;
        }

        double a = k.lengthSquared();
        double b = 2 * k.dotProduct(e);
        double c = e.lengthSquared() - this.radius * this.radius;
        double delta = alignZero(b * b - 4 * a * c);

        if (delta < 0)
            return null;

        if (delta == 0) {
            double t = alignZero((-b)/(2*a));
            if (t <= 0)
                return null;

            List<GeoPoint> intersections = new LinkedList<>();
            intersections.add(new GeoPoint(this, rayStart.add(rayDir.scale(t))));
            return intersections;
        }

        double sDelta = Math.sqrt(delta);
        double t1 = alignZero((-b + sDelta) / (2 * a));
        double t2 = alignZero((-b - sDelta) / (2 * a));
        if (t1 <= 0)
            return null;
        List<GeoPoint> intersections = new LinkedList<>();
        if (t2 > 0 && alignZero(t2 - maxDistance) <= 0)
            intersections.add(new GeoPoint(this, rayStart.add(rayDir.scale(t2))));
        if (alignZero(t1 - maxDistance) <= 0)
            intersections.add(new GeoPoint(this, rayStart.add(rayDir.scale(t1))));
        return intersections;
    }

    @Override
    public String toString() {
        return String.format("Tube {Axis Ray: %s, Radius: %s}", this.axisRay, super.radius);
    }
}