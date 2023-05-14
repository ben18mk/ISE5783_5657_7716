package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * This class is a basic RayTracer
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Finds the color by a given point
     *
     * @param point point
     * @return color
     */
    private Color calcColor(Point point) {
        return this.scene.ambientLight.getIntensity(); // TODO: CHANGE THIS TO THE ACTUAL IMPLEMENTATION (after STAGE 5)
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = this.scene.geometries.findIntersections(ray);

        if (intersections == null)
            return this.scene.background;

        return this.calcColor(ray.findClosestPoint(intersections));
    }
}
