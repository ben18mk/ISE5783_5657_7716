package geometries;

import primitives.*;

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
        return null;
    }

    @Override
    public String toString() {
        return String.format("Tube {Axis Ray: %s, Radius: %s}", this.axisRay, super.radius);
    }
}