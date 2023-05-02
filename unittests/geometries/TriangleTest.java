package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
        // EP01: There is a simple single test here
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 1);

        Triangle triangle = new Triangle(p1, p2, p3);
        Vector normal = triangle.getNormal(p1);

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
        Triangle triangle = new Triangle(
                new Point(1, 0, 0),
                new Point(1, 1, 0),
                new Point(0, 1, 0)
        );

        // ============ Equivalence Partitions Tests ==============
        // EP01: Ray intersects with the Triangle (1 inter. point)
        Ray ray = new Ray(new Point(0.5, 0.75, -2), new Vector(0, 0, 1));
        List<Point> expected = List.of(new Point(0.5, 0.75, 0));
        List<Point> result = triangle.findIntersections(ray);
        assertEquals(
                1,
                result.size(),
                String.format("EP01: Wrong amount of points (Got %d; Must be 1)", result.size())
        );
        assertEquals(expected, result, "EP01: Wrong intersection point");

        // EP02: Ray does not intersect with the triangle (0 inter. points)
        ray = new Ray(new Point(2, 3, -2), new Vector(0, 0, 1));
        result = triangle.findIntersections(ray);
        assertNull(result, "EP02: Wrong amount of points");

        // =============== Boundary Values Tests ==================
        // BVA01: Ray intersects with an edge of the triangle (0 inter. points)
        ray = new Ray(new Point(0.5, 1, 1), new Vector(0, 0, -1));
        result = triangle.findIntersections(ray);
        assertNull(result, "BVA01: Wrong amount of points");

        // BVA02: Ray intersects with a vertex of the triangle (0 inter. points)
        ray = new Ray(new Point(0, 1, 1), new Vector(0, 0, -1));
        result = triangle.findIntersections(ray);
        assertNull(result, "BVA02: Wrong amount of points");

        // BVA03: Ray intersects with an edge of the triangle continuation (0 inter. points)
        ray = new Ray(new Point(-1, 1, 1), new Vector(0, 0, -1));
        result = triangle.findIntersections(ray);
        assertNull(result, "BVA03: Wrong amount of points");

        // BVA04: Ray starts on the triangle
        ray = new Ray(new Point(0.5, 0.75, 0), new Vector(0, 0, 1));
        result = triangle.findIntersections(ray);
        assertNull(result, "BVA04: Wrong amount of points");
    }
}