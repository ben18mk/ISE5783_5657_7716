package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * This class is the base of ray tracing
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * Constructor to initialize this class
     *
     * @param scene scene object
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Returns the color of the first object a given ray intersects with
     *
     * @param ray given ray
     * @return Color
     */
    public abstract Color traceRay(Ray ray);
}
