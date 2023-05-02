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
        Sphere sphere = new Sphere(new Point (1, 0, 0), 1d);
        // ============ Equivalence Partitions Tests ==============
        // EP01: Ray's line is outside the sphere (0 points)
        assertNull(
                sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))),
                "EP01: Ray's line out of sphere"
        );

        // EP02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(
                new Ray(new Point(-1, 0, 0), new Vector(3, 1, 0))
        );

        assertEquals(2, result.size(), "EP02: Wrong number of points");

        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result, "EP02: Ray crosses sphere");

        // EP03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(
                new Ray(new Point(1, 0.5, 0), new Vector(3, 1, 0))
        );

        assertEquals(
                List.of(new Point(1.6851646544245034, 0.7283882181415011, 0)),
                result,
                "EP03: Wrong intersections"
        );

        // EP04: Ray starts after the sphere (0 points)
        result = sphere.findIntersections(
                new Ray(new Point(2, 1, 0), new Vector(3, 1, 0))
        );

        assertNull(result, "EP04: Wrong number of points");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // BVA01: Ray starts at sphere and goes inside (1 point)
        result = sphere.findIntersections(
                new Ray(new Point(0.2, 0.6, 0), new Vector(0, -0.5, 0))
        );

        assertEquals(1, result.size(), "BVA01: Wrong number of points");
        assertEquals(List.of(new Point(0.2, -0.6, 0)), result, "BVA01: Ray crosses sphere");

        // BVA02: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(
                new Ray(new Point(0.2, 0.6, 0), new Vector(0, 1, 0))
        );
        assertNull(result, "BVA02: Wrong number of points");

        // **** Group: Ray's line goes through the center
        // BVA03: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(
                new Ray(new Point(1, 1.1, 0), new Vector(0, -0.5, 0))
        );

        assertEquals(2, result.size(), "BVA03: Wrong number of points");

        if (result.get(0).getY() < result.get(1).getY())
            result = List.of(result.get(1), result.get(0));
        assertEquals(
                List.of(new Point(1, 1, 0),new Point(1, -1, 0)),
                result,
                "BVA03: Ray crosses sphere"
        );

        // BVA04: Ray starts at sphere and goes inside (1 point)
        result = sphere.findIntersections(
                new Ray(new Point(1, 1, 0), new Vector(0, -0.5, 0))
        );

        assertEquals(1, result.size(), "BVA04: Wrong number of points");
        assertEquals(
                List.of(new Point(1, -1, 0)),
                result,
                "BVA04: Ray crosses sphere"
        );

        // BVA05: Ray starts inside (1 point)
        result = sphere.findIntersections(
                new Ray(new Point(1, 0.9, 0), new Vector(0, -0.5, 0))
        );

        assertEquals(1, result.size(), "BVA05: Wrong number of points");
        assertEquals(
                List.of(new Point(1, -1, 0)),
                result,
                "BVA05: Ray crosses sphere"
        );

        // BVA06: Ray starts at the center (1 point)
        result = sphere.findIntersections(
                new Ray(new Point(1, 0, 0), new Vector(0, -0.5, 0))
        );

        assertEquals(1, result.size(), "BVA06: Wrong number of points");
        assertEquals(
                List.of(new Point(1, -1, 0)),
                result,
                "BVA06: Ray crosses sphere"
        );

        // BVA07: Ray starts at sphere and goes outside (0 points)
        result = sphere.findIntersections(
                new Ray(new Point(1, 1, 0), new Vector(0, 0.5, 0))
        );

        assertNull(result, "BVA07: Wrong number of points");

        // BVA08: Ray starts after sphere (0 points)
        result = sphere.findIntersections(
                new Ray(new Point(1, 1.1, 0), new Vector(0, 0.5, 0))
        );

        assertNull(result, "BVA08: Wrong number of points");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // BVA09: Ray starts before the tangent point
        result = sphere.findIntersections(
                new Ray(new Point(2, 1, 0), new Vector(0, -0.5, 0))
        );

        assertNull(result, "BVA09: Wrong number of points");

        // BVA10: Ray starts at the tangent point
        result = sphere.findIntersections(
                new Ray(new Point(2, 0, 0), new Vector(0, -0.5, 0))
        );

        assertNull(result, "BVA10: Wrong number of points");

        // BVA11: Ray starts after the tangent point
        result = sphere.findIntersections(
                new Ray(new Point(2, -1, 0), new Vector(0, -0.5, 0))
        );

        assertNull(result, "BVA11: Wrong number of points");

        // **** Group: Special cases
        // BVA12: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        result = sphere.findIntersections(
                new Ray(new Point(3, 2, 0), new Vector(0, -0.5, 0))
        );
        assertNull(result, "BVA12: Wrong number of points");
    }
}