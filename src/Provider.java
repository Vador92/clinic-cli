public enum Provider {
    PATEL("PATEL", Location.BRIDGEWATER, Specialty.FAMILY),
    LIM("LIM", Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
    ZIMNES("ZIMNES", Location.CLARK, Specialty.FAMILY),
    HARPER("HARPER", Location.CLARK, Specialty.FAMILY),
    KAUR("KAUR",Location.PRINCETON, Specialty.ALLERGIST),
    TAYLOR("TAYLOR",Location.PISCATAWAY, Specialty.PEDIATRICIAN),
    RAMESH("RAMESH", Location.MORRISTOWN, Specialty.ALLERGIST),
    CERAVOLO("CERAVOLO", Location.EDISON, Specialty.PEDIATRICIAN);

    private final String lname;
    private final Location location;
    private final Specialty specialty;

    Provider(String lname, Location location, Specialty specialty) {
        this.lname = lname;
        this.location = location;
        this.specialty = specialty;
    }

    public static Provider setProvider(String provider){
        // normalize provider
        return switch (provider.toUpperCase()) {
            case "PATEL"-> PATEL;
            case "LIM" -> LIM;
            case "ZIMNES" -> ZIMNES;
            case "HARPER" -> HARPER;
            case "KAUR" -> KAUR;
            case "TAYLOR" -> TAYLOR;
            case "RAMESH" -> RAMESH;
            case "CERAVOLO" -> CERAVOLO;
            default -> null; // print error message w/ slot
        };
    }

    @Override
    public String toString() {
        return lname + ", " + location.toString() + ", " + specialty.toString();
    }

//    public Location getLocation() {
//        return location;
//    }
//
//    public Specialty getSpecialty() {
//        return specialty;
//    }

}
