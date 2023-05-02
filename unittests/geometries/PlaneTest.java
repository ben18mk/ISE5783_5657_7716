package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
        // BVA01: Test when 2 points are the same
        assertThrows(
                IllegalArgumentException.class,
                () -> new Plane(p, p, new Point(0, 0, 1)),
                "ERROR: ctor() does not throw an exception when 2 points are the same"
        );

        // BVA02: Test when all 3 points are on the same line
        assertThrows(
                IllegalArgumentException.class,
                () -> new Plane(p, new Point(2, 0, 0), new Point(3, 0, 0)),
                "ERROR: ctor() does not throw an exception when all 3 points are on the same line"
        );
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // EP01: There is a simple single test here
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 1);
        Point p = new Point(-1, 1, 1);

        Plane plane = new Plane(p1, p2, p3);
        Vector normal = plane.getNormal(p);

        assertEquals(1, normal.length(), "ERROR: getNormal() vector is not the unit vector");
        assertTrue(
                normal.equals(new Vector(1, 1, 1).normalize()) ||
                normal.equals(new Vector(-1, -1, -1).normalize()),
                "ERROR: getNormal() wrong value");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}
     */
    @Test
    void testFindIntersections() {
        Plane plane = new Plane(new Point(1, 1, 1), new Vector(1, 1, 1));

        // ============ Equivalence Partitions Tests ==============
        // **** group: Ray is neither parallel nor orthogonal to the plane
        // EP01: Ray intersects the plane (1 inter. point)
        Ray ray = new Ray(new Point(0.5, 0.5, 0.5), new Vector(1, 1, 0));
        List<Point> expected = List.of(new Point(1.25, 1.25, 0.5));
        List<Point> result = plane.findIntersections(ray);
        assertEquals(
                1,
                result.size(),
                String.format("EP01: Wrong amount of points (Got %d; Must be 1)", result.size())
        );
        assertEquals(expected, result, "EP01: Wrong intersection point");

        // EP02: Ray does not intersect with the plane (0 inter. points)
        ray = new Ray(new Point(1.5, 1.5, 1.5), new Vector(1, 1, 0));
        result = plane.findIntersections(ray);
        assertNull(result, "EP02: Wrong amount of points");

        // =============== Boundary Values Tests ==================
        // **** group: Ray is parallel to the plane
        // BVA01: Ray is included in the plane (0 inter. points) // Infinity but null for the correctness
        ray = new Ray(new Point(1, 0, 2), new Vector(0, -1, 1));
        result = plane.findIntersections(ray);
        assertNull(result, "BVA01: Wrong amount of points");

        // BVA02: Ray is not included in the plane (0 inter. points)
        ray = new Ray(new Point(1, 2, 3), new Vector(0, -1, 1));
        result = plane.findIntersections(ray);
        assertNull(result, "BVA02: Wrong amount of points");

        // **** group: Ray is orthogonal to the plane
        // BVA03: Ray starts before the plane (1 inter. point)
        ray = new Ray(new Point(-1, -1, -1), new Vector(1, 1, 1));
        expected = List.of(new Point(1, 1, 1));
        result = plane.findIntersections(ray);
        assertEquals(1,
                result.size(),
                String.format("BVA03: Wrong amount of points (Got %d; Must be 1)", result.size())
        );
        assertEquals(expected, result, "EP01: Wrong intersection point");

        // BVA04: Ray starts on the plane (0 inter. points)
        ray = new Ray(new Point(1, 0, 2), new Vector(1, 1, 1));
        result = plane.findIntersections(ray);
        assertNull(result, "BVA04: Wrong amount of points");

        // BVA05: Ray starts after the plane (0 inter. points)
        ray = new Ray(new Point(2, 2, 2), new Vector(1, 1, 1));
        result = plane.findIntersections(ray);
        assertNull(result, "BVA05: Wrong amount of points");

        // **** group: Other
        // BVA06: Ray starts on the plane but is neither parallel nor orthogonal to the plane (0 inter. points)
        ray = new Ray(new Point(1, 0, 2), new Vector(1, 2, 1));
        result = plane.findIntersections(ray);
        assertNull(result, "BVA06: Wrong amount of points");

        // BVA07: Ray starts on the point defining the plane but is neither parallel nor orthogonal to the plane
        ray = new Ray(new Point(1, 1, 1), new Vector(1, 2, 1));
        result = plane.findIntersections(ray);
        assertNull(result, "BVA07: Wrong amount of points");
    }
}