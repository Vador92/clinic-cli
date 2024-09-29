/**
 * This is an enum class for the Speciality of Provider with the charges.
 * @author Varun Doreswamy
 */
public enum Specialty {
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST( 350);

    private final int charge;

    /**
     * Constructor for Specialty
     * @param charge used to set the charge of the provider
     */
    Specialty(int charge) {
        this.charge = charge;
    }

    /**
     * Returns the name of the enum using name()
     * @return String of enums name
     */
    @Override
    public String toString(){
        return name();
    }

    /**
     * Gets the charge of the enum
     * @return int of the charge for specific specialty
     */
    public int getCharge() {
        return charge;
    }
}
