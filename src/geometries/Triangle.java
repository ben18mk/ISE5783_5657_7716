package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * This class represents a Triangle object
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class Triangle extends Polygon {
    /**
     * Constructor to initialize Triangle object based on 3 Point objects
     *
     * @param p1 Point object 1
     * @param p2 Point object 2
     * @param p3 Point object 3
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point P0 = ray.getStartPoint();
        Vector v = ray.getDirection();

        Vector v1 = super.vertices.get(0).subtract(P0);
        Vector v2 = super.vertices.get(1).subtract(P0);
        Vector v3 = super.vertices.get(2).subtract(P0);

        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();

        if (!(v.dotProduct(n1) > 0 && v.dotProduct(n2) > 0 && v.dotProduct(n3) > 0 ||
                v.dotProduct(n1) < 0 && v.dotProduct(n2) < 0 && v.dotProduct(n3) < 0))
            return null;

        return super.plane.findIntersections(ray);
    }
}