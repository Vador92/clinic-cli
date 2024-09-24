public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    // constructor for Object Profile
    public Profile (String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    // getter method for First Name
    public String getFname() {
        return fname;
    }

    // getter method for Last Name
    public String getLname() {
        return lname;
    }

    // getter method for Object Date of Birth
    public Date getDob() {
        return dob;
    }

    // checks if there are duplicate Profile Objects with the same fields, or null fields
    public boolean equals(Object object) {
        if(this == object) {
            return true;
        }

        if(object == null || getClass() != object.getClass()) {
            return false;
        }

        Profile other = (Profile) object;

        if (fname == null || lname == null || dob == null) {
            return false;
        }

        return fname.equalsIgnoreCase(other.fname)
                && lname.equalsIgnoreCase(other.lname)
                && dob.equals(other.dob);
    }

    @Override
    public String toString() {
        return getFname() + " " + getLname() + " " + getDob();
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

    // profile p = new profile (first, last, dob)
    // check for dob if it is before today
}
