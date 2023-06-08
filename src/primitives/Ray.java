package primitives;

import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * This class is base for all classes using rays
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    private static final double DELTA = 0.1;

    /**
     * Constructor to initialize Ray based object with its start Point and direction Vector
     *
     * @param p0 start Point
     * @param dir direction Vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Constructor to initialize Ray based object when the head is moved by DELTA
     *
     * @param head head of the ray - starting point of the ray
     * @param direction ray direction
     * @param normal normal to the geometry
     */
    public Ray(Point head, Vector direction, Vector normal) {
        this.p0 = head.add(normal.scale(normal.dotProduct(direction) > 0 ? DELTA : -DELTA));
        this.dir = direction.normalize();
    }

    /**
     * Get the starting Point object of the Ray object
     *
     * @return starting Point object of the Ray object
     */
    public Point getStartPoint() {
        return this.p0;
    }

    /**
     * Get the direction Vector object of the Ray object
     *
     * @return direction Vector object of the Ray object
     */
    public Vector getDirection() {
        return this.dir;
    }

    public Point getPoint(double t) throws IllegalArgumentException {
        if (t <= 0)
            throw new IllegalArgumentException("The t argument must be a positive number");

        return this.p0.add(this.dir.scale(t));
    }

    /**
     * Finds the closes point to this ray's starting point out of a given list of points
     *
     * @param points list of points
     * @return closes point this ray's starting point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Finds the closes GeoPoint to this ray's starting point out of a given list of points
     *
     * @param points list of GeoPoints
     * @return closes GeoPoint this ray's starting point
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        if (points == null || points.size() == 0)
            return null;

        GeoPoint closest = points.get(0);
        double minDistanceSquared = closest.point.distanceSquared(this.p0);
        for (GeoPoint point : points) {
            if (point.point.distanceSquared(this.p0) < minDistanceSquared) {
                closest = point;
                minDistanceSquared = closest.point.distanceSquared(this.p0);
            }
        }

        return closest;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Ray other)
            return this.p0.equals(other.p0) && this.dir.equals(other.dir);
        return false;
    }

    @Override
    public String toString() {
        return String.format("Ray {Start: %s, Direction: %s}", this.p0, this.dir);
    }
}
