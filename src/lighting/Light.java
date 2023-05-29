package lighting;

import primitives.Color;

/**
 * This class is an abstract of Light
 *
 * @author Benjamin Mamistvalov, Eyal Nathan
 */
abstract class Light {
    private final Color intensity;

    /**
     * Constructor to initialize a Light class
     *
     * @param intensity intensity color
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
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
