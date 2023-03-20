package geometries;

import primitives.Point;

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
}