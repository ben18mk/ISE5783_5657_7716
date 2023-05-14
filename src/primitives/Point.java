package primitives;

/**
 * This class is the base for all classes using points
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class Point {
    protected final Double3 xyz;

    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * Constructor to initialize Point based object with its three number values
     *
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Point(double x, double y, double z) {
        this.xyz = new Double3(x, y, z);
    }

    /**
     * Constructor to initialize Point based object with its Double3 object
     *
     * @param xyz Double3 object
     */
    protected Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Gets the x value
     *
     * @return x value
     */
    public double getX() {
        return this.xyz.d1;
    }

    /**
     * Gets the y value
     *
     * @return y value
     */
    public double getY() {
        return this.xyz.d2;
    }

    /**
     * Gets the z value
     *
     * @return z value
     */
    public double getZ() {
        return this.xyz.d3;
    }

    /**
     * Subtract this Point object with another Point object and result with a new Vector object representing the delta
     * between these 2 Point objects
     *
     * @param other right handle side operand for subtraction
     * @return result Vector object of the subtraction
     */
    public Vector subtract(Point other) {
        return new Vector(this.xyz.subtract(other.xyz));
    }

    /**
     * Add a Vector object to this Point object and result with a new Point
     *
     * @param vector right handle side operand for addition
     * @return result Point object of the addition
     */
    public Point add(Vector vector) {
        return new Point(this.xyz.add(vector.xyz));
    }

    /**
     * Calculate the squared distance this Point object and another Point object
     *
     * @param other the Point object to calculate the squared distance with
     * @return squared distance between the two Point objects
     */
    public double distanceSquared(Point other) {
        double deltaX = other.xyz.d1 - this.xyz.d1;
        double deltaY = other.xyz.d2 - this.xyz.d2;
        double deltaZ = other.xyz.d3 - this.xyz.d3;
        return deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
    }

    /**
     * Calculate the distance this Point object and another Point object
     *
     * @param other the Point object to calculate the distance with
     * @return distance between the two Point objects
     */
    public double distance(Point other) {
        return Math.sqrt(this.distanceSquared(other));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Point other)
            return this.xyz.equals(other.xyz);
        return false;
    }

    @Override
    public String toString() {
        return "Point " + xyz;
    }
}