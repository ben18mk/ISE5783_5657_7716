package primitives;

import org.junit.jupiter.api.Test;

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
}