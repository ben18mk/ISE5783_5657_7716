package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Tube
 * @author Benjamin Mamistvalov
 */
class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}
     */
    @Test
    void testGetNormal() {
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 0, 0));
        Tube tube = new Tube(ray, 2);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that the point is on the tube
        assertEquals(
                new Vector(0, 0, 1),
                tube.getNormal(new Point(2, 0, 2)),
                "ERROR: getNormal() wrong value when point is on the tube"
        );

        // =============== Boundary Values Tests ==================
        // TC10: Test point parallel to the ray start
        assertEquals(
                new Vector(0, 0, 1),
                tube.getNormal(new Point(0, 0, 2)),
                "ERROR: getNormal() wrong value when point is parallel to the ray start"
        );
    }
}