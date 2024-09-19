public enum Specialty {
    FAMILY("Family"),
    PEDIATRICIAN("Pediatrician"),
    ALLERGIST("Allergist");

    private final String speciality;

    Specialty(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return this.speciality;
    }
}
