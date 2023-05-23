package geometries;

/**
 * This class is the base for all radial geometric classes
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public abstract class RadialGeometry extends Geometry {
    protected final double radius;

    /**
     * Constructor to initialize RadialGeometry based object with its radius
     *
     * @param radius radius of the geometric object
     * @throws IllegalArgumentException if the radius is less or equals to 0
     */
    public RadialGeometry(double radius) {
        if (radius <= 0)
            throw new IllegalArgumentException("The radius must be greater than 0");
        this.radius = radius;
    }
}
