public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    //might need getter functions here to make the fname, lname, and dob public variables if the patient class needs it

    public Profile (String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }

        if(object == null || getClass() != object.getClass()) {
            return false;
        }

        Profile other = (Profile) object;
        // change later this most likely is wrong
        return fname.equalsIgnoreCase(other.fname) && lname.equalsIgnoreCase(other.lname) && dob.equals(other.dob);
    }

    @Override
    public String toString() {
        return fname + " " + lname + " " + dob;
    }


    @Override
    public int compareTo(Profile other) {
        int lnameCompare = lname.compareToIgnoreCase(other.lname); //checks for duplicate lnames
        if(lnameCompare != 0) {
            return lnameCompare; //if duplicates not found return lname
        }

        int fnameCompare = fname.compareToIgnoreCase(other.fname);
        if(fnameCompare != 0) {
            return fnameCompare;
        }

        return dob.compareTo(other.dob);
    }
}
