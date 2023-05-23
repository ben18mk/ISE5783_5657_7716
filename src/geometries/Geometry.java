package geometries;

import primitives.*;

/**
 * This interface is the base for all geometric classes
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public abstract class Geometry extends Intersectable {
    protected Color emission = Color.BLACK;

    /**
     * Find the normal of the geometry at the given point on the surface of the geometry.
     *
     * @param point point on the geometry
     * @return normal of the geometry
     */
    public abstract Vector getNormal(Point point);

    /**
     * Gets the emission color of this geometry
     *
     * @return emission color of this geometry
     */
    public Color getEmission() {
        return this.emission;
    }

    /**
     * Sets the emission color of this geometry
     *
     * @param emission new emission color
     * @return the updated Geometry object
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
}
