package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Testing Vector
 * @author Benjamin Mamistvalov
 */
class VectorTest {

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}
     */
    @Test
    void testAdd() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(1, 0, 0);

        // TC01: Test that the result vector of add() is proper
        assertEquals(new Vector(2, 2, 3), v1.add(v2), "ERROR: add() wrong value");

        // =============== Boundary Values Tests ==================
        // TC10: Test zero vector from vector adding v1 + -v1
        assertThrows(
                IllegalArgumentException.class,
                () -> v1.add(new Vector(-1, -2, -3)),
                "ERROR: Vector + -itself does not throw an exception"
        );
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}
     */
    @Test
    void testScale() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the result vector of scale() with a positive scalar is proper
        assertEquals(new Vector(2, 4, 6), v1.scale(2), "ERROR: scale() wrong value");

        // TC02: Test that the result vector of scale() with a negative scalar is proper
        assertEquals(new Vector(-2, -4, -6), v1.scale(-2), "ERROR: scale() wrong value");

        // =============== Boundary Values Tests ==================
        // TC10: Test zero vector from scaling a vector by 0
        assertThrows(
                IllegalArgumentException.class,
                () -> v1.scale(0),
                "ERROR: Vector scaled by 0 does not throw an exception"
        );
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}
     */
    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);

        // TC01: Test that the dot-product of orthogonal vectors is 0
        assertTrue(isZero(v1.dotProduct(v2)), "ERROR: dotProduct() for orthogonal vectors is not zero");

        v2 = new Vector(-2, -4, -6);

        // TC02: Test that the result of the dot-product is proper
        assertEquals(-28, v1.dotProduct(v2), "ERROR: dotProduct() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}
     */
    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that the length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(
                v1.length() * v2.length(),
                vr.length(),
                0.00001,
                "ERROR: crossProduct() wrong result length"
        );

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(
                isZero(vr.dotProduct(v1)),
                "ERROR: crossProduct() result is not orthogonal to the 1st operand"
        );
        assertTrue(
                isZero(vr.dotProduct(v2)),
                "ERROR: crossProduct() result is not orthogonal to the 2nd operand"
        );

        // =============== Boundary Values Tests ==================
        // TC10: Test zero vector from cross-product of co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(
                IllegalArgumentException.class,
                () -> v1.crossProduct(v3),
                "ERROR: crossProduct() for parallel vectors does not throw an exception"
        );
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1, 2, 3);

        // TC01: Test that the result of the length-squared is proper
        assertEquals(14, v1.lengthSquared(), 0.00001, "ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#length()}
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(0, 3, 4);

        // TC01: Test that the result of the length is proper
        assertEquals(5, v.length(), 0.00001, "ERROR: length() wrong value");
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();

        // TC01: Test that the length of a normalized vector is 1
        assertEquals(1, u.length(), "ERROR: the normalized vector is not a unit vector");

        // TC02: Test that normalized vector is not opposite to the original one
        assertFalse(
                v.dotProduct(u) < 0,
                "ERROR: the normalized vector is opposite to the original one"
        );

        // =============== Boundary Values Tests ==================
        // TC10: Test zero vector from cross-product of co-lined vectors
        assertThrows(
                IllegalArgumentException.class,
                () -> v.crossProduct(u),
                "ERROR: the normalized vector is not parallel to the original one"
        );
    }

    /**
     * Test method for {@link primitives.Vector#rotateX(double)}
     */
    @Test
    void testRotateX() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);

        // TC01: Rotate 45 degrees
        assertEquals(
                new Vector(1, -0.7071067811865, 3.535533905933),
                v.rotateX(45),
                "TC01: 45 degrees rotation - Wrong vector"
        );

        // TC02: Rotate 90 degrees
        assertEquals(
                new Vector(1, -3, 2),
                v.rotateX(90),
                "TC02: 90 degrees rotation - Wrong vector"
        );

        // TC03: Rotate 135 degrees
        assertEquals(
                new Vector(1, -3.535533905933, -0.7071067811865),
                v.rotateX(135),
                "TC03: 135 degrees rotation - Wrong vector"
        );

        // TC04: Rotate 180 degrees
        assertEquals(
                new Vector(1, -2, -3),
                v.rotateX(180),
                "TC04: 180 degrees rotation - Wrong vector"
        );

        // TC05: Rotate 225 degrees
        assertEquals(
                new Vector(1, 0.7071067811865, -3.535533905933),
                v.rotateX(225),
                "TC05: 225 degrees rotation - Wrong vector"
        );

        // TC06: Rotate 270 degrees
        assertEquals(
                new Vector(1, 3, -2),
                v.rotateX(270),
                "TC06: 270 degrees rotation - Wrong vector"
        );

        // TC07: Rotate 315 degrees
        assertEquals(
                new Vector(1, 3.535533905933, 0.7071067811865),
                v.rotateX(315),
                "TC07: 315 degrees rotation - Wrong vector"
        );

        // =============== Boundary Values Tests ==================
        // TC10: Rotate 0 degrees
        assertEquals(
                v,
                v.rotateX(0),
                "TC10: 0 degrees rotation - Wrong vector"
        );

        // TC11: Rotate 360 degrees
        assertEquals(
                v,
                v.rotateX(360),
                "TC11: 360 degrees rotation - Wrong vector"
        );

        // TC12: Rotate 405 degrees
        assertEquals(
                new Vector(1, -0.7071067811865, 3.535533905933),
                v.rotateX(405),
                "TC12: 405 degrees rotation - Wrong vector"
        );

        // TC13: Rotate -90 degrees
        assertEquals(
                new Vector(1, 3, -2),
                v.rotateX(-90),
                "TC13: -90 degrees rotation - Wrong vector"
        );

        // TC14: Rotate -360 degrees
        assertEquals(
                v,
                v.rotateX(-360),
                "TC14: -360 degrees rotation - Wrong vector"
        );
    }

    /**
     * Test method for {@link primitives.Vector#rotateY(double)}
     */
    @Test
    void testRotateY() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);

        // TC01: Rotate 45 degrees
        assertEquals(
                new Vector(2.828427124746, 2, 1.414213562373),
                v.rotateY(45),
                "TC01: 45 degrees rotation - Wrong vector"
        );

        // TC02: Rotate 90 degrees
        assertEquals(
                new Vector(3, 2, -1),
                v.rotateY(90),
                "TC02: 90 degrees rotation - Wrong vector"
        );

        // TC03: Rotate 135 degrees
        assertEquals(
                new Vector(1.414213562373, 2, -2.828427124746),
                v.rotateY(135),
                "TC03: 135 degrees rotation - Wrong vector"
        );

        // TC04: Rotate 180 degrees
        assertEquals(
                new Vector(-1, 2, -3),
                v.rotateY(180),
                "TC04: 180 degrees rotation - Wrong vector"
        );

        // TC05: Rotate 225 degrees
        assertEquals(
                new Vector(-2.828427124746, 2, -1.414213562373),
                v.rotateY(225),
                "TC05: 225 degrees rotation - Wrong vector"
        );

        // TC06: Rotate 270 degrees
        assertEquals(
                new Vector(-3, 2, 1),
                v.rotateY(270),
                "TC06: 270 degrees rotation - Wrong vector"
        );

        // TC07: Rotate 315 degrees
        assertEquals(
                new Vector(-1.414213562373, 2, 2.828427124746),
                v.rotateY(315),
                "TC07: 315 degrees rotation - Wrong vector"
        );

        // =============== Boundary Values Tests ==================
        // TC10: Rotate 0 degrees
        assertEquals(
                v,
                v.rotateY(0),
                "TC10: 0 degrees rotation - Wrong vector"
        );

        // TC11: Rotate 360 degrees
        assertEquals(
                v,
                v.rotateY(360),
                "TC11: 360 degrees rotation - Wrong vector"
        );

        // TC12: Rotate 405 degrees
        assertEquals(
                new Vector(2.828427124746, 2, 1.414213562373),
                v.rotateY(405),
                "TC12: 405 degrees rotation - Wrong vector"
        );

        // TC13: Rotate -90 degrees
        assertEquals(
                new Vector(-3, 2, 1),
                v.rotateY(-90),
                "TC13: -90 degrees rotation - Wrong vector"
        );

        // TC14: Rotate -360 degrees
        assertEquals(
                v,
                v.rotateY(-360),
                "TC14: -360 degrees rotation - Wrong vector"
        );
    }

    /**
     * Test method for {@link primitives.Vector#rotateZ(double)}
     */
    @Test
    void testRotateZ() {
        // ============ Equivalence Partitions Tests ==============
        Vector v = new Vector(1, 2, 3);

        // TC01: Rotate 45 degrees
        assertEquals(
                new Vector(-0.7071067811865, 2.121320343560, 3),
                v.rotateZ(45),
                "TC01: 45 degrees rotation - Wrong vector"
        );

        // TC02: Rotate 90 degrees
        assertEquals(
                new Vector(-2, 1, 3),
                v.rotateZ(90),
                "TC02: 90 degrees rotation - Wrong vector"
        );

        // TC03: Rotate 135 degrees
        assertEquals(
                new Vector(-2.121320343560, -0.7071067811865, 3),
                v.rotateZ(135),
                "TC03: 135 degrees rotation - Wrong vector"
        );

        // TC04: Rotate 180 degrees
        assertEquals(
                new Vector(-1, -2, 3),
                v.rotateZ(180),
                "TC04: 180 degrees rotation - Wrong vector"
        );

        // TC05: Rotate 225 degrees
        assertEquals(
                new Vector(0.7071067811865, -2.121320343560, 3),
                v.rotateZ(225),
                "TC05: 225 degrees rotation - Wrong vector"
        );

        // TC06: Rotate 270 degrees
        assertEquals(
                new Vector(2, -1, 3),
                v.rotateZ(270),
                "TC06: 270 degrees rotation - Wrong vector"
        );

        // TC07: Rotate 315 degrees
        assertEquals(
                new Vector(2.121320343560, 0.7071067811865, 3),
                v.rotateZ(315),
                "TC07: 315 degrees rotation - Wrong vector"
        );

        // =============== Boundary Values Tests ==================
        // TC10: Rotate 0 degrees
        assertEquals(
                v,
                v.rotateZ(0),
                "TC10: 0 degrees rotation - Wrong vector"
        );

        // TC11: Rotate 360 degrees
        assertEquals(
                v,
                v.rotateZ(360),
                "TC11: 360 degrees rotation - Wrong vector"
        );

        // TC12: Rotate 405 degrees
        assertEquals(
                new Vector(-0.7071067811865, 2.121320343560, 3),
                v.rotateZ(405),
                "TC12: 405 degrees rotation - Wrong vector"
        );

        // TC13: Rotate -90 degrees
        assertEquals(
                new Vector(2, -1, 3),
                v.rotateZ(-90),
                "TC13: -90 degrees rotation - Wrong vector"
        );

        // TC14: Rotate -360 degrees
        assertEquals(
                v,
                v.rotateZ(-360),
                "TC14: -360 degrees rotation - Wrong vector"
        );
    }
}