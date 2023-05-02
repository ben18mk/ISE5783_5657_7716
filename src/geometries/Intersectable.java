package geometries;

import java.util.List;
import primitives.*;

/**
 * This interface is the base for all intersectable object classes
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public interface Intersectable {
    /**
     * Finds the intersections of a given ray
     * with other geometries in its path
     *
     * @param ray The intersections inquiry ray
     * @return List of intersection points
     */
    public List<Point> findIntersections(Ray ray);
}