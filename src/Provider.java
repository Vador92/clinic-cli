public enum Provider {
    PATEL(Location.BRIDGEWATER, Specialty.FAMILY),
    LIM(Location.BRIDGEWATER, Specialty.PEDIATRICIAN),
    ZIMNES(Location.CLARK, Specialty.FAMILY),
    HARPER(Location.CLARK, Specialty.FAMILY),
    KAUR(Location.PRINCETON, Specialty.ALLERGIST),
    TAYLOR(Location.PISCATAWAY, Specialty.PEDIATRICIAN),
    RAMESH(Location.MORRISTOWN, Specialty.ALLERGIST),
    CERAVOLO(Location.EDISON, Specialty.PEDIATRICIAN);

    private final Location location;
    private final Specialty specialty;

    Provider(Location location, Specialty specialty) {
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
        return name() + ", " + location.toString() + ", " + specialty.toString();
    }

//    public Location getLocation() {
//        return location;
//    }
//
//    public Specialty getSpecialty() {
//        return specialty;
//    }

}
