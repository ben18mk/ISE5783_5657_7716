package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
        // TC01: There is a simple single test here
        Point center = new Point(0, 0, 0);
        Sphere sphere = new Sphere(center, 2);

        Vector normal = sphere.getNormal(new Point(2, 0, 0));

        assertEquals(1, normal.length(), "ERROR: getNormal() vector is not the unit vector");
        assertEquals(normal, new Vector(1, 0, 0), "ERROR: getNormal() wrong value");
    }
}