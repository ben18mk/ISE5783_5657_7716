package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * This interface represents a Light Source
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public interface LightSource {
    /**
     * Gets the intensity Color at a specific point
     *
     * @param p point
     * @return intensity Color
     */
    public Color getIntensity(Point p);

    /**
     * Gets the direction of the light source at a specific point
     *
     * @param p point
     * @return direction Vector
     */
    public Vector getL(Point p);

    /**
     * Calculates the distance between the point and the light source
     *
     * @param point point
     * @return distance
     */
    public double getDistance(Point point);
}
