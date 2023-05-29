package geometries;

import java.util.List;
import primitives.*;

/**
 * This interface is the base for all intersectable object classes
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public abstract class Intersectable {
    /**
     * This static class represents a GeoPoint
     *
     * @author Benjamin Mamistvalov, Eyal Nathan
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * Constructor to initialize the GeoPoint class
         *
         * @param geometry geometry
         * @param point point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof GeoPoint geoPoint)) return false;
            return this.geometry == geoPoint.geometry && this.point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return String.format("GeoPoint {geometry = %s, point = %s}", this.geometry, this.point);
        }
    }

    /**
     * Finds the intersections of a given ray
     * with other geometries in its path
     *
     * @param ray The intersections inquiry ray
     * @return List of intersection points
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
}