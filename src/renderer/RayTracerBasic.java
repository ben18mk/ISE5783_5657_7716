package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
     * @param point geo point
     * @param ray ray
     * @return color
     */
    private Color calcColor(GeoPoint point, Ray ray) {
        return point.geometry.getEmission()
                .add(this.scene.ambientLight.getIntensity())
                .add(calcLocalEffects(point, ray));
    }

    /**
     * Calculates the local effect (diffusive and specular) by a given GeoPoint
     *
     * @param point geo point
     * @param ray ray
     * @return color of the effect
     */
    private Color calcLocalEffects(GeoPoint point, Ray ray) {
        Vector v = ray.getDirection();
        Vector n = point.geometry.getNormal(point.point);

        double nv = alignZero(n.dotProduct(v));
        if (isZero(nv))
            return Color.BLACK;

        Material material = point.geometry.getMaterial();
        int nShininess = material.nShininess;
        Double3 kd = material.kD;
        Double3 ks = material.kS;

        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(point.point);
            double nl = alignZero(n.dotProduct(l));

            if (nl * nv > 0) {
                Color lightIntensity = lightSource.getIntensity(point.point);
                color = color.add(calcDiffusive(kd, nl, lightIntensity),
                                    calcSpecular(ks, nShininess, n, l, nl, v, lightIntensity));
            }
        }
        return color;
    }

    /**
     * Calculates the diffusive effect
     *
     * @param kd kd
     * @param nl dot product of the normal of the geometry with the light direction
     * @param lightIntensity light intensity color
     * @return diffusive effect color
     */
    private Color calcDiffusive(Double3 kd, double nl, Color lightIntensity) {
        return lightIntensity.scale(kd.scale(Math.abs(nl)));
    }

    /**
     * Calculates the specular effect
     *
     * @param ks ks
     * @param nShininess shininess
     * @param n normal vector to the geometry
     * @param l light direction vector
     * @param nl dot product of the normal of the geometry with the light direction
     * @param v vector from the camera to the geo point
     * @param lightIntensity light intensity color
     * @return specular effect color
     */
    private Color calcSpecular(Double3 ks, int nShininess, Vector n, Vector l,
                               double nl, Vector v, Color lightIntensity) {
        Vector minusR = n.scale(2 * nl).subtract(l);
        double minusVR = minusR.dotProduct(v);
        if (minusVR <= 0)
            return Color.BLACK;
        return lightIntensity.scale(ks.scale(Math.pow(minusVR, nShininess)));
    }

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(ray);

        if (intersections == null)
            return this.scene.background;

        return this.calcColor(ray.findClosestGeoPoint(intersections), ray);
    }
}
