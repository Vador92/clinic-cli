/**
 * @author Varun
 */
public enum Specialty {
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST( 350);

    private final int charge;

    Specialty(int charge) {
        this.charge = charge;
    }

    @Override
    public String toString(){
        return name();
    }

    public int getCharge() {
        return charge;
    }

}
