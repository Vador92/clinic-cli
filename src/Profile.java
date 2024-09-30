/**
 * @author Yuet
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    // constructor for Object Profile
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    private Profile getProfile(String fname, String lname, String dob) {
        try {
            Date birthday = new Date(dob);
            if (birthday.isValid()) {
                return new Profile(fname, lname, new Date(dob));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
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
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
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


    // need to change compareTo method
    @Override
    public int compareTo(Profile other) {
        /*int lnameCompare = lname.compareToIgnoreCase(other.lname); //checks for duplicate lnames
        if (lnameCompare != 0) {
            return lnameCompare; //if duplicates not found return lname
        }

        int fnameCompare = fname.compareToIgnoreCase(other.fname);
        if (fnameCompare != 0) {
            return fnameCompare;
        }

        return dob.compareTo(other.dob);*/

        // Compare last names first
        int lnameCompare = lname.compareToIgnoreCase(other.lname);
        if (lnameCompare > 0) {
            return 1;  // Current last name is greater
        } else if (lnameCompare < 0) {
            return -1; // Current last name is smaller
        }

        // If last names are equal, compare first names
        int fnameCompare = fname.compareToIgnoreCase(other.fname);
        if (fnameCompare > 0) {
            return 1;  // Current first name is greater
        } else if (fnameCompare < 0) {
            return -1; // Current first name is smaller
        }

        // If both last and first names are equal, compare date of birth
        int dobCompare = dob.compareTo(other.dob);
        if (dobCompare > 0) {
            return 1;  // Current date of birth is later (greater)
        } else if (dobCompare < 0) {
            return -1; // Current date of birth is earlier (smaller)
        }

        // If all fields (last name, first name, and date of birth) are equal
        return 0;
    }


    public static void main(String[] args) {
        // Test case 1: Last name of calling object is lexicographically greater
        Profile p1 = new Profile("John", "Smith", new Date("01/01/2000"));
        Profile p2 = new Profile("John", "Anderson", new Date("01/01/2000"));
        System.out.println("Test Case 1 - Expected: 1, Actual: " + p1.compareTo(p2));

        // Test case 2: First name of calling object is lexicographically greater (last names are the same)
        Profile p3 = new Profile("Zara", "Johnson", new Date("01/01/2000"));
        Profile p4 = new Profile("Alice", "Johnson", new Date("01/01/2000"));
        System.out.println("Test Case 2 - Expected: 1, Actual: " + p3.compareTo(p4));

        // Test case 3: Date of birth of calling object is later when both last and first names are the same
        Profile p5 = new Profile("John", "Smith", new Date("05/01/2000"));
        Profile p6 = new Profile("John", "Smith", new Date("01/01/2000"));
        System.out.println("Test Case 3 - Expected: 1, Actual: " + p5.compareTo(p6));

        // Test case 4: Last name of calling object is lexicographically smaller
        Profile p7 = new Profile("John", "Anderson", new Date("01/01/2000"));
        Profile p8 = new Profile("John", "Smith", new Date("01/01/2000"));
        System.out.println("Test Case 4 - Expected: -1, Actual: " + p7.compareTo(p8));

        // Test case 5: First name of calling object is lexicographically smaller (last names are the same)
        Profile p9 = new Profile("Alice", "Johnson", new Date("01/01/2000"));
        Profile p10 = new Profile("Zara", "Johnson", new Date("01/01/2000"));
        System.out.println("Test Case 5 - Expected: -1, Actual: " + p9.compareTo(p10));

        // Test case 6: Date of birth of calling object is earlier when both last and first names are the same
        Profile p11 = new Profile("John", "Smith", new Date("01/01/2000"));
        Profile p12 = new Profile("John", "Smith", new Date("05/01/2000"));
        System.out.println("Test Case 6 - Expected: -1, Actual: " + p11.compareTo(p12));

        // Test case 7: Last name, first name, and date of birth are identical
        Profile p13 = new Profile("John", "Smith", new Date("01/01/2000"));
        Profile p14 = new Profile("John", "Smith", new Date("01/01/2000"));
        System.out.println("Test Case 7 - Expected: 0, Actual: " + p13.compareTo(p14));
    }
}
