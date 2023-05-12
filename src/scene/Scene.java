package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.Color;

/**
 * This class represents a Scene
 *
 * @author Benjamin Mamistvalov
 */
public class Scene {
    public final String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;
    public Geometries geometries = new Geometries();

    /**
     * Constructor to initialize a scene with its name
     *
     * @param name scene name
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * Setter to set the background of the scene
     *
     * @param background color
     * @return this Scene object
     */
    Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Setter to set the Ambient Light of the scene
     *
     * @param ambientLight object
     * @return this Scene object
     */
    Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Setter to set the geometries of the scene
     *
     * @param geometries object
     * @return this Scene object
     */
    Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }
}
