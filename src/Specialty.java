public enum Specialty {
    FAMILY("Family"),
    PEDIATRICIAN("Pediatrician"),
    ALLERGIST("Allergist");

    private final String Specialty;

    Specialty(String Specialty) {
        this.Specialty = Specialty;
    }

    @Override
    public String toString() {
        return Specialty;
    }
}
