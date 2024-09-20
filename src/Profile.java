public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    public Profile (String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
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
