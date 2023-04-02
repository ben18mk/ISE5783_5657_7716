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
}