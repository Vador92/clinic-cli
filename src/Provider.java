/**
 * This is an enum class for the 8 providers, with location and specialty.
 * @author Varun Doreswamy
 */
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

    /**
     * This is the constructor that is set based on location and specialty.
     * @param location used to set the location of the provider
     * @param specialty used to set the specialty of the provider
     */
    Provider(Location location, Specialty specialty) {
        this.location = location;
        this.specialty = specialty;
    }

    /**
     * This method is used to set a provider based on the string input.
     * @param provider is used to check which provider it is - case ignorance
     * @return Provider depending on match with the set ones, or null
     */
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

    /**
     * Method generates a readable formatted string of the provider.
     * @return String with name, location, and specialty.
     */
    @Override
    public String toString() {
        return "[" + name() + ", " + location.toString()
                + ", " + specialty.toString() + "]";
    }

    /**
     * This method gets the provider's specialty
     * @return Specialty of the provider
     */
    public Specialty getSpecialty(){
        return specialty;
    }

    /**
     * Returns the location of the provider, used to compare appointments
     * @return Location of the provider
     */
    public Location getLocation(){
        return location;
    }


}
