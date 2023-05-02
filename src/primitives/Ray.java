package primitives;

/**
 * This class is base for all classes using rays
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * Constructor to initialize Ray based object with its start Point and direction Vector
     *
     * @param p0 start Point
     * @param dir direction Vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * Get the starting Point object of the Ray object
     *
     * @return starting Point object of the Ray object
     */
    public Point getStartPoint() {
        return this.p0;
    }

    /**
     * Get the direction Vector object of the Ray object
     *
     * @return direction Vector object of the Ray object
     */
    public Vector getDirection() {
        return this.dir;
    }

    public Point getPoint(double t) throws IllegalArgumentException {
        if (t <= 0)
            throw new IllegalArgumentException("The t argument must be a positive number");

        return this.p0.add(this.dir.scale(t));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Ray other)
            return this.p0.equals(other.p0) && this.dir.equals(other.dir);
        return false;
    }

    @Override
    public String toString() {
        return String.format("Ray {Start: %s, Direction: %s}", this.p0, this.dir);
    }
}
