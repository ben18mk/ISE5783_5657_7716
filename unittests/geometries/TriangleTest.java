package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Triangle
 * @author Benjamin Mamistvalov
 */
class TriangleTest {

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 1);

        Triangle triangle = new Triangle(p1, p2, p3);
        Vector normal = triangle.getNormal(p1);

        assertEquals(1, normal.length(), "ERROR: getNormal() vector is not the unit vector");
        assertTrue(
                normal.equals(new Vector(1, 1, 1)) ||
                        normal.equals(new Vector(-1, -1, -1)),
                "ERROR: getNormal() wrong value");
    }
}