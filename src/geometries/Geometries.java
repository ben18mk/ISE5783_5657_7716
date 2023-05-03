package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private final List<Intersectable> geometries;

    public Geometries() {
        this.geometries = new LinkedList<>();
    }

    public Geometries(Intersectable... geometries) {
        this.geometries = List.of(geometries);
    }

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
