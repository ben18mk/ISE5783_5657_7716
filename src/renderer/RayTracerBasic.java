package renderer;

import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

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
     * Finds the color by a given GeoPoint
     *
     * @param point Geo Point
     * @return color
     */
    private Color calcColor(GeoPoint point) {
        return point.geometry.getEmission().add(this.scene.ambientLight.getIntensity());
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(ray);

        if (intersections == null)
            return this.scene.background;

        return this.calcColor(ray.findClosestGeoPoint(intersections));
    }
}
