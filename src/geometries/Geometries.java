package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a collection of geometries
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class Geometries implements Intersectable {
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

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;

        for (Intersectable geometry: this.geometries) {
            List<Point> points = geometry.findIntersections(ray);

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
