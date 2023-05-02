package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Cylinder
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}
     */
    @Test
    void testGetNormal() {
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Cylinder cylinder = new Cylinder(10, ray, 2);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Test when the point is on the tube of the cylinder
        assertEquals(
                new Vector(1, 0, 0),
                cylinder.getNormal(new Point(2, 0, 5)),
                "ERROR: getNormal() wrong value when point is on the tube o the cylinder"
        );

        // TC02: Test when the point is on the topper base of the cylinder
        assertEquals(
                new Vector(0, 0, 1),
                cylinder.getNormal(new Point(0, -3, 10)),
                "ERROR: getNormal() wrong value when point is on the topper base of the cylinder"
        );

        // TC03: Test when the point is on the bottom base of the cylinder
        assertEquals(
                new Vector(0, 0, -1),
                cylinder.getNormal(new Point(0, -3, 0)),
                "ERROR: getNormal() wrong value when point is on the bottom base of the cylinder"
        );

        // =============== Boundary Values Tests ==================
        // TC10: Test when the point is the center of the topper base of the cylinder
        assertEquals(
                new Vector(0, 0, 1),
                cylinder.getNormal(new Point(0, 0, 10)),
                "ERROR: getNormal() wrong value when point is on the center of the cylinder topper base"
        );

        // TC10: Test when the point is the center of the bottom base of the cylinder
        assertEquals(
                new Vector(0, 0, -1),
                cylinder.getNormal(new Point(0, 0, 0)),
                "ERROR: getNormal() wrong value when point is on the center of the cylinder bottom base"
        );
    }
}
