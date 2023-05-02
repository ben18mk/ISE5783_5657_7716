package geometries;

import primitives.*;

/**
 * This interface is the base for all geometric classes
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public interface Geometry extends Intersectable {
    /**
     * Find the normal of the geometry at the given point on the surface of the geometry.
     *
     * @param point point on the geometry
     * @return normal of the geometry
     */
    public Vector getNormal(Point point);
}
