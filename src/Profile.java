public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

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
    public int compareTo(Profile o) {
        return 0;
    }
}
