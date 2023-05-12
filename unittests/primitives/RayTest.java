package primitives;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Ray
 * @author Benjamin Mamistvalov
 */
class RayTest {
    
    /**
     * Test method for {@link primitives.Ray#getPoint(double)}
     */
    @Test
    void testGetPoint() {
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // EP01: Positive t, i.e. the point is on the ray
        assertEquals(new Point(2, 0, 0), ray.getPoint(2), "EP01: Wrong Point");

        // =============== Boundary Values Tests ==================
        // BVA01: t = 0, i.e the point is the start point of the ray
        assertThrows(
                IllegalArgumentException.class,
                () -> ray.getPoint(0),
                "BVA01: The start point is unacceptable"
        );

        // BVA02: t < 0, i.e. the point is behind the ray
        assertThrows(
                IllegalArgumentException.class,
                () -> ray.getPoint(-1),
                "BVA02: A point behind the ray is unacceptable"
        );
    }

    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List<primitives.Point>)}
     */
    @Test
    void testFindClosestPoint() {
        Ray ray = new Ray(new Point(1, 1, 1), new Vector(2, 2, 2));

        // ============ Equivalence Partitions Tests ==============
        // EP01: The list's middle point is the closest to the ray's starting point
        Point closest = new Point(1, 2, 1);
        List<Point> points = List.of(
                new Point(1, 4, 2),
                new Point(2, 2, 2),
                new Point(1, 2, 3),
                closest,
                new Point(3, 2, 1),
                new Point(4, 3, 1),
                new Point(3, 5, 1)
        );

        assertEquals(closest, ray.findClosestPoint(points), "EP01: Wrong closest point");

        // =============== Boundary Values Tests ==================
        // BVA01: The list is empty
        points = new ArrayList<>();

        assertNull(ray.findClosestPoint(points), "BVA01: Wrong value for empty list");
        assertNull(ray.findClosestPoint(null), "BVA01: Wrong value for null list");

        // BVA02: The first point is the closes
        points = List.of(
                closest,
                new Point(1, 4, 2),
                new Point(2, 2, 2),
                new Point(1, 2, 3),
                new Point(3, 2, 1),
                new Point(4, 3, 1),
                new Point(3, 5, 1)
        );

        assertEquals(closest, ray.findClosestPoint(points), "BVA02: Wrong closest point");

        // BVA03: The last point is the closest
        points = List.of(
                new Point(1, 4, 2),
                new Point(2, 2, 2),
                new Point(1, 2, 3),
                new Point(3, 2, 1),
                new Point(4, 3, 1),
                new Point(3, 5, 1),
                closest
        );

        assertEquals(closest, ray.findClosestPoint(points), "BVA03: Wrong closest point");
    }
}