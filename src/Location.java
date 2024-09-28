/**
 * @author Varun
 */
public enum Location {
    BRIDGEWATER("Somerset", "08807"),
    EDISON("Middlesex", "08817"),
    PISCATAWAY("Middlesex", "08854"),
    PRINCETON("Mecrer", "08542"),
    MORRISTOWN("Morris", "07960"),
    CLARK("Union", "07066");

    private final String county;
    private final String zip;

    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return name() + ", " + getCounty() + " " + getZip();
    }

    public String getCounty() {
        return county;
    }
    public String getZip() {
        return zip;
    }
}
