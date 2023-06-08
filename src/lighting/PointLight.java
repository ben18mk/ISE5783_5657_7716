package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    protected final Point position;
    private double kC, kL, kQ;

    /**
     * Constructor to initialize a Point Light class
     *
     * @param intensity intensity Color
     * @param position position Point
     */
    protected PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
        this.kC = 1;
        this.kL = 0;
        this.kQ = 0;
    }

    /**
     * Sets kC
     *
     * @param kC new kC
     * @return the updated Point Light object
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Sets kL
     *
     * @param kL new kL
     * @return the updated Point Light object
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Sets kQ
     *
     * @param kQ new kQ
     * @return the updated Point Light object
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        double distanceSquared = this.position.distanceSquared(p);
        return this.getIntensity()
                .scale((double) 1 / (this.kC + this.kL * Math.sqrt(distanceSquared) + this.kQ * distanceSquared));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(this.position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return this.position.distance(point);
    }
}
