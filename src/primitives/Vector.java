package primitives;

/**
 * This class is the base for all classes using vectors
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class Vector extends Point {
    /**
     * Constructor to initialize Vector based object with its three number values
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Vector(double x, double y, double z) {
        super(x,y,z);

        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("The Zero Vector is illegal");
    }

    /**
     * Constructor to initialize Vector based object with its Double3 object.
     *
     * @param xyz Double3 object
     * @throws IllegalArgumentException if the Zero Vector is provided
     */
    protected Vector(Double3 xyz) {
        super(xyz);

        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("The Zero Vector is illegal");
    }

    /**
     * Sum this Vector object with another Vector object
     *
     * @param other right handle side operand for addition
     * @return a new Vector object representing the summed Vector of the 2 Vector objects
     */
    public Vector add(Vector other) {
        return new Vector(this.xyz.add(other.xyz));
    }

    /**
     * Scale (Multiply) this Vector object by a scalar
     *
     * @param scalar the scalar number for the scaling
     * @return a new Vector object representing the scaling result
     */
    public Vector scale(double scalar) {
        return new Vector(this.xyz.scale(scalar));
    }

    /**
     * Product of this Vector object and another Vector object into a scalar number
     *
     * @param other another Vector object to product with
     * @return scalar number resulting from the product
     */
    public double dotProduct(Vector other) {
        return this.xyz.d1 * other.xyz.d1 + this.xyz.d2 * other.xyz.d2 + this.xyz.d3 * other.xyz.d3;
    }

    /**
     * Product of this Vector object and another Vector object into a new Vector object
     * This new Vector object is orthogonal to both Vector objects used for the production, thus normal to the plane
     * containing those Vector objects
     *
     * @param other another Vector object to product with
     * @return orthogonal vector
     */
    public Vector crossProduct(Vector other) {
        double i = this.xyz.d2 * other.xyz.d3 - this.xyz.d3 * other.xyz.d2;
        double j = this.xyz.d1 * other.xyz.d3 - this.xyz.d3 * other.xyz.d1;
        double k = this.xyz.d1 * other.xyz.d2 - this.xyz.d2 * other.xyz.d1;

        return new Vector(i, -j, k);
    }

    /**
     * Calculate the squared length of this Vector object
     *
     * @return the squared length of this Vector object
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     * Calculate the length of this Vector object
     *
     * @return the length of this Vector object
     */
    public double length() {
        return Math.sqrt(this.lengthSquared());
    }

    /**
     * Normalize this Vector object
     * The length of the returned Vector will be 1
     *
     * @return the normalized vector
     */
    public Vector normalize() {
        return new Vector(this.xyz.reduce(this.length()));
    }

    /**
     * Rotates this Vector on the X axis
     *
     * @param angle angle of rotation
     * @return the rotated vector
     */
    public Vector rotateX(double angle) {
        double sin = Math.sin(Math.toRadians(angle));
        double cos = Math.cos(Math.toRadians(angle));

        double x = this.xyz.d1;
        double y = cos * this.xyz.d2 - sin * this.xyz.d3;
        double z = sin * this.xyz.d2 + cos * this.xyz.d3;

        return new Vector(x, y, z);
    }

    /**
     * Rotates this Vector on the Y axis
     *
     * @param angle angle of rotation
     * @return the rotated vector
     */
    public Vector rotateY(double angle) {
        double sin = Math.sin(Math.toRadians(angle));
        double cos = Math.cos(Math.toRadians(angle));

        double x = cos * this.xyz.d1 + sin * this.xyz.d3;
        double y = this.xyz.d2;
        double z = -sin * this.xyz.d1 + cos * this.xyz.d3;

        return new Vector(x, y, z);
    }

    /**
     * Rotates this Vector on the Z axis
     *
     * @param angle angle of rotation
     * @return the rotated vector
     */
    public Vector rotateZ(double angle) {
        double sin = Math.sin(Math.toRadians(angle));
        double cos = Math.cos(Math.toRadians(angle));

        double x = cos * this.xyz.d1 - sin * this.xyz.d2;
        double y = sin * this.xyz.d1 + cos * this.xyz.d2;
        double z = this.xyz.d3;

        return new Vector(x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Vector other)
            return super.equals(other);
        return false;
    }

    @Override
    public String toString() {
        return "Vector " + this.xyz;
    }
}
