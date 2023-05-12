package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * This class represents the Ambient Lighting of a scene
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
public class AmbientLight {
    private final Color intensity;

    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

    /**
     * Constructor to initialize the Ambient Light Class
     *
     * @param iA lighting Color
     * @param kA intensity scale vector
     */
    public AmbientLight(Color iA, Double3 kA) {
        this.intensity = iA.scale(kA);
    }

    /**
     * Constructor to initialize the Ambient Light Class
     *
     * @param iA lighting Color
     * @param kA intensity scale for all Color components
     */
    public AmbientLight(Color iA, double kA) {
        this.intensity = iA.scale(kA);
    }

    /**
     * Gets the intensity Color
     *
     * @return intensity Color
     */
    public Color getIntensity() {
        return this.intensity;
    }
}
