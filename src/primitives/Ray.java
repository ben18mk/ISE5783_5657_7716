package primitives;

public class Ray {
    private final Point p0;
    private final Vector dir;

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
