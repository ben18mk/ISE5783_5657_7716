package primitives;

public class Material {
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;
    public int nShininess = 0;


    /**
     * Sets the kD of the Material
     *
     * @param kD new kD vector
     * @return the updated Material object
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Sets the kD of the Material
     *
     * @param kD new kD
     * @return the updated Material object
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Sets the kS of the Material
     *
     * @param kS new kS vector
     * @return the updated Material object
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Sets the kS of the Material
     *
     * @param kS new kS
     * @return the updated Material object
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Sets the shininess of the Material
     *
     * @param nShininess new shininess
     * @return the updated Material object
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
