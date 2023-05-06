package renderer;

import primitives.*;

import static primitives.Util.*;

/**
 * This class represents a camera that will capture the image created with the geometries
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class Camera {
    private Point position;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private int height;
    private int width;
    private double distance;


    /**
     * Camera object constructor
     *
     * @param position the Point on which the camera is based
     * @param vTo the Vector of where the camera is looking at
     * @param vUp the Vector of the upwards direction of the camera
     * @throws IllegalArgumentException if vTo and vUp are not orthogonal
     */
    public Camera(Point position, Vector vTo, Vector vUp) throws IllegalArgumentException {
        if (!isZero(vTo.dotProduct(vUp)))
            throw new IllegalArgumentException("vTo and vUp must be orthogonal to each other");

        this.position = position;
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = this.vTo.crossProduct(this.vUp);
    }


    /**
     * Get the position of the camera
     *
     * @return the position Point of the camera
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * Get the Vector of where the camera is looking at (vTo)
     * @return the vTo Vector
     */
    public Vector getVTo() {
        return this.vTo;
    }

    /**
     * Get the Vector of the upwards direction of the camera (vUp)
     *
     * @return the vUp Vector
     */
    public Vector getVUp() {
        return this.vUp;
    }

    /**
     * Get the Vector of the rightwards direction of the camera (vRight)
     *
     * @return the vRight Vector
     */
    public Vector getVRight() {
        return this.vRight;
    }

    /**
     * Get the View Plane height
     *
     * @return View Plane height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Get the View Plane width
     *
     * @return View Plane width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Get the distance of the camera from the View Plane
     *
     * @return the distance of the camera from the View Plane
     */
    public double getDistance() {
        return this.distance;
    }


    /**
     * Set the View Plane size
     *
     * @param width View Plane width
     * @param height View Plane height
     * @return the updated Camera object
     */
    public Camera setVPSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Set the View Plane distance from the camera
     *
     * @param distance distance from the camera
     * @return the updated Camera object
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }


    /**
     * Creates the ray that passes at the center of the requested pixel on the View Plane
     *
     * @param nX amount of column in the View Plane
     * @param nY amount of rows in the View Plane
     * @param j pixel column index
     * @param i pixel row index
     * @return the ray that passes at the center of the requested pixel on the View Plane
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        // Image Center
        Point pCenter = this.position.add(this.vTo.scale(this.distance));

        // Ratio (pixel width & height)
        double rY = (double)this.height / nY;
        double rX = (double)this.width / nX;

        // Pixel[i, j] center
        double yI = -(i - (double)(nY - 1) / 2) * rY;
        double xJ = (j - (double)(nX - 1) / 2) * rX;
        // Point pIJ = pC.add(this.vRight.scale(xJ).add(this.vUp.scale(yI)));
        Point pIJ = pCenter;
        if (xJ != 0) pIJ = pIJ.add(this.vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(this.vUp.scale(yI));

        return new Ray(this.position, pIJ.subtract(this.position));
    }

    /**
     * Moves the camera by a Vector
     *
     * @param moveDirection the movement vector
     * @return the updated Camera object
     */
    public Camera moveCamera(Vector moveDirection) {
        this.position = this.position.add(moveDirection);
        return this;
    }

    /**
     * Moves the camera to another Point
     *
     * @param newPosition new position point
     * @return the updated Camera object
     */
    public Camera moveCamera(Point newPosition) {
        this.position = newPosition;
        return this;
    }

    /**
     * Rotates the camera on the X axis
     *
     * @param angle rotation angle in degrees
     * @return the updated Camera object
     */
    public Camera rotateX(double angle) {
        this.vTo = this.vTo.rotateX(angle);
        this.vUp = this.vUp.rotateX(angle);
        this.vRight = this.vRight.rotateX(angle);
        return this;
    }

    /**
     * Rotates the camera on the Y axis
     *
     * @param angle rotation angle in degrees
     * @return the updated Camera object
     */
    public Camera rotateY(double angle) {
        this.vTo = this.vTo.rotateY(angle);
        this.vUp = this.vUp.rotateY(angle);
        this.vRight = this.vRight.rotateY(angle);
        return this;
    }

    /**
     * Rotates the camera on the Z axis
     *
     * @param angle rotation angle in degrees
     * @return the updated Camera object
     */
    public Camera rotateZ(double angle) {
        this.vTo = this.vTo.rotateZ(angle);
        this.vUp = this.vUp.rotateZ(angle);
        this.vRight = this.vRight.rotateZ(angle);
        return this;
    }
}
