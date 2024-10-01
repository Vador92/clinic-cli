package ruclinicscheduler;

/**
 * This is an enum class for the 6 specific locations for the clinics.
 * @author Varun Doreswamy
 */
public enum Location {
    BRIDGEWATER("Somerset", "08807"),
    EDISON("Middlesex", "08817"),
    PISCATAWAY("Middlesex", "08854"),
    PRINCETON("Mercer", "08542"),
    MORRISTOWN("Morris", "07960"),
    CLARK("Union", "07066");

    // instance variables
    private final String county;
    private final String zip;

    /**
     * This is the constructor method for ruclinicscheduler.Location using the county and zip
     * @param county used for first argument of enum
     * @param zip used for second argument of enum
     */
     Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    /**
     * This class returns a formatted string based on name, county, zip
     * @return String for printing details about location
     */
    @Override
    public String toString() {
        return name() + ", " + getCounty() + " " + getZip();
    }

    /**
     * This method returns the county of the enum
     * @return String of the county of the current object
     */
    public String getCounty() {
        return county;
    }

    /**
     * This method returns the zip of the enum
     * @return String of the zip of the current object
     */
    public String getZip() {
        return zip;
    }
}
