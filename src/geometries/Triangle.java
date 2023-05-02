package geometries;

import primitives.Point;
import primitives.Ray;

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

    // TODO: Maybe remove if imp in Polygon
    @Override
    public List<Point> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }
}