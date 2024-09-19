public enum Provider {
    PATEL(Location.BRIDGEWATER, Specialty.FAMILY),
    LIM(Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
    ZIMNES(Location.CLARK, Specialty.FAMILY),
    HARPER(Location.CLARK, Specialty.FAMILY),
    KAUR(Location.PRINCETON, Specialty.ALLERGIST),
    TAYLOR(Location.PISCATAWAY, Specialty.PEDIATRICIAN),
    RAMESH(Location.MORRISTOWN, Specialty.ALLERGIST),
    CERAVOLO(Location.EDISON, Specialty.PEDIATRICIAN);

    /*
        Patel, Bridgewater, Family
        Lim, Bridgewater, Pediatrician
        Zimnes, Clark, Family
        Harper, Clark, Family
        Kaur, Princeton, Allergist
        Taylor, Piscataway, Pediatrician
        Ramesh, Morristown, Allergist
        Ceravolo, Edison, Pediatrician
     */

    private final Location location;
    private final Specialty specialty;

    Provider(Location location, Specialty specialty) {
        this.location = location;
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return this.location + ", " + this.specialty;
    }

    public Location getLocation() {
        return this.location;
    }

    public Specialty getSpecialty() {
        return this.specialty;
    }

}
