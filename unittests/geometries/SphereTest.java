package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Sphere
 * @author Benjamin Mamistvalov
 */
class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // EP01: There is a simple single test here
        Point center = new Point(0, 0, 0);
        Sphere sphere = new Sphere(center, 2);

        Vector normal = sphere.getNormal(new Point(2, 0, 0));

        assertEquals(1, normal.length(), "ERROR: getNormal() vector is not the unit vector");
        assertEquals(normal, new Vector(1, 0, 0), "ERROR: getNormal() wrong value");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}
     */
    @Test
    void testFindIntersections() {
        Sphere sphere = new Sphere(new Point(0, 0, 0), 2);

        // ============ Equivalence Partitions Tests ==============
        // EP01: Ray before the sphere and does not cross it (0 inter. points)
        Ray ray = new Ray(new Point(-4, 0, 0), new Vector(1, 1, 0));
        List<Point> result = sphere.findIntersections(ray);
        assertNull(result, "EP01: Wrong amount of intersections");

        // EP02: Ray starts before the sphere and crosses it (2 inter. points)
        ray = new Ray(new Point(3, 1, 0), new Vector(-1, -1, 0));
        List<Point> expected = List.of(new Point(2, 0, 0), new Point(0, -2, 0));
        result = sphere.findIntersections(ray);
        assertEquals(
                2,
                result.size(),
                String.format("EP02: Wrong amount of points (Got %d; Must be 2)", result.size())
                );
        assertEquals(expected, result, "EP02: Wrong intersection points");

        // EP03: Ray starts inside the sphere (1 inter. point)
        ray = new Ray(new Point(1, 0, 0), new Vector(-1, -2, 0));
        expected = List.of(new Point(0, -2, 0));
        result = sphere.findIntersections(ray);
        assertEquals(
                1,
                result.size(),
                String.format("EP03: Wrong amount of points (Got %d; Must be 1)", result.size())
        );
        assertEquals(expected, result, "EP03: Wrong intersection points");

        // EP04: Ray starts after the sphere (0 inter. points)
        ray = new Ray(new Point(0, -3, 0), new Vector(-1, -2, 0));
        result = sphere.findIntersections(ray);
        assertNull(result, "EP04: Wrong amount of intersections");

        // =============== Boundary Values Tests ==================
        // BV01: Ray starts on the sphere and goes inside (1 inter. point)
        ray = new Ray(new Point(0, -2, 0), new Vector(0, 1, 0));
        expected = List.of(new Point(0, 2, 0));
        result = sphere.findIntersections(ray);
        assertEquals(
                1,
                result.size(),
                String.format("BV01: Wrong amount of points (Got %d; Must be 1)", result.size())
        );
        assertEquals(expected, result, "BV01: Wrong intersection points");

        // BV02: Ray starts on the sphere and goes outside (0 inter. points)
        ray = new Ray(new Point(0, -2, 0), new Vector(0, -1, 0));
        result = sphere.findIntersections(ray);
        assertNull(result, "BV02: Wrong amount of intersections");

        // BV03: Ray starts before the sphere and goes through the center (2 inter. points)
        ray = new Ray(new Point(3, 3, 3), new Vector(-3, -3, -3));
        expected = List.of(new Point(1.15, 1.15, 1.15), new Point(-1.15, -1.15, -1.15));
        result = sphere.findIntersections(ray);
        assertEquals(
                2,
                result.size(),
                String.format("BV03: Wrong amount of points (Got %d; Must be 2)", result.size())
        );
        assertEquals(expected, result, "BV03: Wrong intersection points");

        // BV04: Ray starts on the sphere and goes inside it through the center (1 inter. point)
        ray = new Ray(new Point(1.15, 1.15, 1.15), new Vector(-3, -3, -3));
        expected = List.of(new Point(-1.15, -1.15, -1.15));
        result = sphere.findIntersections(ray);
        assertEquals(
                1,
                result.size(),
                String.format("BV04: Wrong amount of points (Got %d; Must be 1)", result.size())
        );
        assertEquals(expected, result, "BV04: Wrong intersection points");

        // BV05: Ray starts inside the sphere and goes through the center (1 inter. point)
        ray = new Ray(new Point(0.5, 0.5, 0.5), new Vector(-3, -3, -3));
        expected = List.of(new Point(-1.15, -1.15, -1.15));
        result = sphere.findIntersections(ray);
        assertEquals(
                1,
                result.size(),
                String.format("BV05: Wrong amount of points (Got %d; Must be 1)", result.size())
        );
        assertEquals(expected, result, "BV05: Wrong intersection points");

        // BV06: Ray starts at the center of the sphere (1 inter. points)
        ray = new Ray(new Point(0, 0, 0), new Vector(-3, -3, -3));
        expected = List.of(new Point(-1.15, -1.15, -1.15));
        result = sphere.findIntersections(ray);
        assertEquals(
                1,
                result.size(),
                String.format("BV06: Wrong amount of points (Got %d; Must be 1)", result.size())
        );
        assertEquals(expected, result, "BV06: Wrong intersection points");

        // BV07: Ray starts on the sphere and goes outside anti-center wise (0 inter. points)
        ray = new Ray(new Point(-1.15, -1.15, -1.15), new Vector(-3, -3, -3));
        result = sphere.findIntersections(ray);
        assertNull(result, "BV07: Wrong amount of intersections");

        // BV08: Ray starts after the sphere and goes anti-center wise (0 inter. points)
        ray = new Ray(new Point(-3, -3, -3), new Vector(-3, -3, -3));
        result = sphere.findIntersections(ray);
        assertNull(result, "BV08: Wrong amount of intersections");

        // BV09: Ray starts before the tangent point (0 inter. points)
        ray = new Ray(new Point(2, 2, 2), new Vector(-1, -1, 0));
        result = sphere.findIntersections(ray);
        assertNull(result, "BV09: Wrong amount of intersections");

        // BV10: Ray starts on the tangent point (0 inter. points)
        ray = new Ray(new Point(0, 0, 2), new Vector(-1, -1, 0));
        result = sphere.findIntersections(ray);
        assertNull(result, "BV10: Wrong amount of intersections");

        // BV11: Ray starts after the tangent point (0 inter. points)
        ray = new Ray(new Point(-2, -2, 2), new Vector(-1, -1, 0));
        result = sphere.findIntersections(ray);
        assertNull(result, "BV11: Wrong amount of intersections");

        // BV12: Ray does not cross the Sphere, it is orthogonal to the sphere (0 inter. points)
        ray = new Ray(new Point(2, 2, 4), new Vector(-1, -1, 0));
        result = sphere.findIntersections(ray);
        assertNull(result, "BV12: Wrong amount of intersections");
    }
}