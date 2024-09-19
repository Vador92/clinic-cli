public enum Location {
    BRIDGEWATER("Somerset County", "08807"),
    EDISON("Middlesex County", "08817"),
    PISCATAWAY("Middlesex County", "08854"),
    PRINCETON("Merer County", "08542"),
    MORRISTOWN("Morris County", "07960"),
    CLARK("Union County", "07066");

    private final String county;
    private final String zip;

    Location(String county, String zip) {
        this.county = county;
        this.zip = zip;
    }

    @Override
    public String toString() {
        return this.getCounty() + ", " + this.getZip();
    }

    public String getCounty() {
        return this.county;
    }
    public String getZip() {
        return this.zip;
    }

    public static void main(String[] args){

    }

}
