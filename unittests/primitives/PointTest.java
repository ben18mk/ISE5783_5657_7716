package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Testing Point
 * @author Benjamin Mamistvalov
 */
class PointTest {

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}
     */
    @Test
    void testSubtract() {
        Point p1 = new Point(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Point p2 = new Point(-2, -4, -6);

        // TC01: Test that the result of subtract() is proper
        assertEquals(
                new Vector(3, 6, 9),
                p1.subtract(p2),
                "ERROR: Point - Point does not work correctly"
        );
        assertEquals(
                new Vector(1, 1, 1),
                new Point(2, 3, 4).subtract(p1),
                "ERROR: Point - Point does not work correctly"
        );

        // =============== Boundary Values Tests ==================
        // TC10: Test zero point from point subtraction p1 - p1
        assertThrows(
                IllegalArgumentException.class,
                ()->p1.subtract(p1),
                "ERROR: Point - itself does not throw an exception"
        );
    }

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}
     */
    @Test
    void testAdd() {
        Point p1 = new Point(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the result of add() is proper
        assertEquals(
                new Point(0, 0, 0),
                p1.add(new Vector(-1, -2, -3)),
                "ERROR: Point + Vector does not work correctly"
        );
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1, 2, 3);

        // TC01: Test that the distance-squared between 2 points is proper
        Point p2 = new Point(6, 4, 2);
        assertEquals(
                30,
                p1.distanceSquared(p2),
                "ERROR: distanceSquared() between 2 points wrong value"
        );

        // TC02: Test that the distance-squared between a point with itself is proper
        assertTrue(isZero(p1.distanceSquared(p1)), "ERROR: distanceSquared() between same points wrong value");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        Point p1 = new Point(1, 2, 3);

        // TC01: Test that the distance between 2 points is proper
        Point p2 = new Point(6, 4, 2);
        assertEquals(
                5.477225575,
                p1.distance(p2),
                0.00001,
                "ERROR: distance() between 2 points wrong value"
        );

        // TC02: Test that the distance between a point with itself is proper
        assertTrue(isZero(p1.distance(p1)), "ERROR: distance() between same points wrong value");
    }
}