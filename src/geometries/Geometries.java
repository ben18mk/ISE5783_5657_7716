package geometries;

import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a collection of geometries
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class Geometries extends Intersectable {
    private final List<Intersectable> geometries;

    /**
     * Default constructor to initialize a Geometries object
     */
    public Geometries() {
        this.geometries = new LinkedList<>();
    }

    /**
     * Constructor to initialize a Geometry object given some geometries
     *
     * @param geometries given geometries
     */
    public Geometries(Intersectable... geometries) {
        this.geometries = List.of(geometries);
    }

    /**
     * Adds geometries to the collection
     *
     * @param geometries geometries to add
     */
    public void add(Intersectable... geometries) {
        this.geometries.addAll(List.of(geometries));
    }

    /**
     * Adds geometries to the collection
     *
     * @param others other geometries objects
     */
    public void add(Geometries... others) {
        for (Geometries other : others) {
            this.geometries.addAll(other.geometries);
        }
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> result = null;

        for (Intersectable geometry: this.geometries) {
            List<GeoPoint> points = geometry.findGeoIntersections(ray, maxDistance);

            if (points == null)
                continue;

            if (result == null)
                result = new LinkedList<>(points);
            else
                result.addAll(points);
        }

        return result;
    }
}
