public enum Specialty {
    FAMILY("Family", 250),
    PEDIATRICIAN("Pediatrician", 300),
    ALLERGIST("Allergist", 350);

    private final String Specialty;
    private final int charge;

    Specialty(String Specialty, int charge) {
        this.Specialty = Specialty;
        this.charge = charge;
    }

    public String getSpecialty() {
        return Specialty;
    }

    public int getCharge() {
        return charge;
    }

}
