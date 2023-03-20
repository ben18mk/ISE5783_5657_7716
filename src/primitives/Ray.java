package primitives;

/**
 * This class is base of all classes using rays
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     * Constructor to initialize Ray based object with its start Point and direction Vection
     *
     * @param p0 start Point
     * @param dir direction Vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Ray other)
            return this.p0.equals(other.p0) && this.dir.equals(other.dir);;
        return false;
    }

    @Override
    public String toString() {
        return String.format("Ray {Start = %s, Direction = %s}", this.p0, this.dir);
    }
}
