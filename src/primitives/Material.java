package primitives;

public class Material {
    public Double3 kD = Double3.ZERO;
    public Double3 kS = Double3.ZERO;
    public Double3 kT = Double3.ZERO;
    public Double3 kR = Double3.ZERO;
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
     * Sets the kT of the Material
     *
     * @param kT new kT vector
     * @return the updated Material object
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Sets the kT of the Material
     *
     * @param kT new kT
     * @return the updated Material object
     */
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * Sets the kR of the Material
     *
     * @param kR new kR vector
     * @return the updated Material object
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Sets the kR of the Material
     *
     * @param kR new kR
     * @return the updated Material object
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
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
