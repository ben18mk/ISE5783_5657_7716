package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Plane
 * @author Benjamin Mamistvalov
 */
class PlaneTest {
    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}
     */
    @Test
    void testConstructor() {
        Point p = new Point(1, 0, 0);

        // =============== Boundary Values Tests ==================
        // TC10: Test when 2 points are the same
        assertThrows(
                IllegalArgumentException.class,
                ()->new Plane(p, p, new Point(0, 0, 1)),
                "ERROR: ctor() does not throw an exception when 2 points are the same"
        );

        // TC11: Test when all 3 points are on the same line
        assertThrows(
                IllegalArgumentException.class,
                ()->new Plane(p, new Point(2, 0, 0), new Point(3, 0, 0)),
                "ERROR: ctor() does not throw an exception when all 3 points are on the same line"
        );
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 1);
        Point p = new Point(-1, 1, 1);

        Plane plane = new Plane(p1, p2, p3);
        Vector normal = plane.getNormal(p);

        assertEquals(1, normal.length(), "ERROR: getNormal() vector is not the unit vector");
        assertTrue(
                normal.equals(new Vector(1, 1, 1)) ||
                normal.equals(new Vector(-1, -1, -1)),
                "ERROR: getNormal() wrong value");
    }
}