public enum Location {
    BRIDGEWATER("BRIDGEWATER", "Somerset County", "08807"),
    EDISON("EDISON","Middlesex County", "08817"),
    PISCATAWAY("PISCATAWAY","Middlesex County", "08854"),
    PRINCETON("PRINCETON","Merer County", "08542"),
    MORRISTOWN("MORRISTOWN","Morris County", "07960"),
    CLARK("CLARK","Union County", "07066");

    private final String city;
    private final String county;
    private final String zip;

    Location(String city, String county, String zip) {
        this.city = city;
        this.county = county;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return getCity() + ", " + getCounty() + ", " + getZip();
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }
    public String getZip() {
        return zip;
    }
}
