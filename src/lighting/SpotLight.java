package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static primitives.Util.alignZero;

public class SpotLight extends PointLight {
    private final Vector direction;
    private int narrowBeam = 1;

    /**
     * Constructor to initialize a SpotLight class
     *
     * @param intensity intensity Color
     * @param position  position Point
     * @param direction direction Vector
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * Set the narrower beam - used for flashlights
     *
     * @param i the narrowness of the beam
     * @return the updated SpotLight object
     */
    public PointLight setNarrowBeam(int i) {
        this.narrowBeam = i; // (cos(angle)) ^ i to get the narrow beam
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double v = this.direction.dotProduct(this.getL(p));
        if (alignZero(v) < 0)
            return Color.BLACK;

        return super.getIntensity(p).scale(this.narrowBeam == 1 ? v : Math.pow(v, this.narrowBeam));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(super.position).normalize();
    }
}
