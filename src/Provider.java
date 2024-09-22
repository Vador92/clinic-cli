public enum Provider {
    PATEL("Patel", Location.BRIDGEWATER, Specialty.FAMILY),
    LIM("Lim", Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
    ZIMNES("Zimnes", Location.CLARK, Specialty.FAMILY),
    HARPER("Harper", Location.CLARK, Specialty.FAMILY),
    KAUR("Kaur",Location.PRINCETON, Specialty.ALLERGIST),
    TAYLOR("Taylor",Location.PISCATAWAY, Specialty.PEDIATRICIAN),
    RAMESH("Ramesh", Location.MORRISTOWN, Specialty.ALLERGIST),
    CERAVOLO("Ceravold", Location.EDISON, Specialty.PEDIATRICIAN);

    private final String lname;
    private final Location location;
    private final Specialty specialty;

    Provider(String lname, Location location, Specialty specialty) {
        this.lname = lname;
        this.location = location;
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return lname + location.toString() + "," + specialty.toString();
    }

//    public Location getLocation() {
//        return location;
//    }
//
//    public Specialty getSpecialty() {
//        return specialty;
//    }

}
