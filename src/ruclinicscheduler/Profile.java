package ruclinicscheduler;

/**
 * This is the Profile class which manages all the profiles for each patient
 * The profiles of each patient contain a first name, last name, and dob
 * This also checks for additional edge cases building off the Date class
 * @author Yuet Yue, Varun Doreswamy
 */
public class Profile implements Comparable<Profile> {

    // constants for lexicographical checking for variable differences
    private static final int LEXICO_GREATER = 1;
    private static final int LEXICO_LESSER = -1;
    private static final int LEXICO_EQUAL = 0;
    private static final int EQUAL = 0;

    // instance variables
    private String fname;
    private String lname;
    private Date dob;

    /**
     * This is the constructor method for creating patient profiles
     * The profile contains the first and last name along with dob
     * @param fname gets read as the first name and added into the object
     * @param lname gets read as the last name and added into the object
     * @param dob gets read as the date of birth and added into the object
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * This method acts as a getter method for the profile
     * @param fname gets read as the first name
     * @param lname gets read as the last name
     * @param dob gets read as the date of birth
     * @return the new profile object containing fname, lname, and dob
     */
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

    /**
     * This method acts as a getter for the first name in the profile
     * @return the patient's first name
     */
    public String getFname() {
        return fname;
    }

    /**
     * This method acts as a getter for the last name in the profile
     * @return the patient's last name
     */
    public String getLname() {
        return lname;
    }

    /**
     * This method acts as a getter for the date of the dob in the profile
     * @return the date value of the patient's date of birth
     */
    public Date getDob() {
        return dob;
    }

    /**
     * This method checks for duplicate profile objects
     * @param object to compare another profile object for duplicate checking
     * @return TRUE if the profile is a duplicate, FALSE if it is different
     */
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

    /**
     * This method returns the profile in the proper String format
     * @return String of the first name, last name, and dob of the patient
     */
    @Override
    public String toString() {
        return getFname() + " " + getLname() + " " + getDob();
    }


    /**
     * This method compares the values within profile for sorting purposes
     * @param other the object to be compared with the fname, lname, and dob
     * @return -1, 1, or 0 if lexicographically lesser, greater, or equal
     */
    @Override
    public int compareTo(Profile other) {

        // Compare last names first
        int lnameCompare = lname.compareToIgnoreCase(other.lname);
        if (lnameCompare > LEXICO_EQUAL) {
            return LEXICO_GREATER;
        } else if (lnameCompare < LEXICO_EQUAL) {
            return LEXICO_LESSER;
        }

        // If last names are equal, compare first names
        int fnameCompare = fname.compareToIgnoreCase(other.fname);
        if (fnameCompare > LEXICO_EQUAL) {
            return LEXICO_GREATER;
        } else if (fnameCompare < LEXICO_EQUAL) {
            return LEXICO_LESSER;
        }

        // If both last and first names are equal, compare date of birth
        int dobCompare = dob.compareTo(other.dob);
        if (dobCompare > LEXICO_EQUAL) {
            return LEXICO_GREATER;
        } else if (dobCompare < LEXICO_EQUAL) {
            return LEXICO_LESSER;
        }

        return EQUAL;
    }

    /**
     * This main method acts as te testbed main() for this class
     * Test Edge Cases:
     * 1. Last name is different and lexicographically greater
     * 2. First name is different and lexicographically greater
     * 3. Date of birth is different, later, and lexicographically greater
     * 4. Last name is different and lexicographically lesser
     * 5. First name is different and lexicographically lesser
     * 6. Date of birth is different, earlier, and lexicographically lesser
     * 7. First name, last name, and date of birth are identical
     * @param args as the input of profiles used for testing edge cases
     */
    public static void main(String[] args) {
        // Test case 1: Last name of calling
        // object is lexicographically greater
        Profile p1 = new Profile(
                "John",
                "Doe",
                new Date("01/01/2000")
        );
        Profile p2 = new Profile(
                "John",
                "Anderson",
                new Date("01/01/2000")
        );
        System.out.println(
                "Test Case 1 - Expected: 1, Actual: "
                        + p1.compareTo(p2)
        );

        // Test case 2: First name of calling object is
        // lexicographically greater (last names are the same)
        Profile p3 = new Profile(
                "John",
                "Doe",
                new Date("01/01/2000")
        );
        Profile p4 = new Profile(
                "Jane",
                "Doe",
                new Date("01/01/2000")
        );
        System.out.println(
                "Test Case 2 - Expected: 1, Actual: "
                        + p3.compareTo(p4)
        );

        // Test case 3: Date of birth of calling object is later
        // when both last and first names are the same
        Profile p5 = new Profile(
                "John",
                "Doe",
                new Date("05/01/2000")
        );
        Profile p6 = new Profile(
                "John",
                "Doe",
                new Date("01/01/2000")
        );
        System.out.println(
                "Test Case 3 - Expected: 1, Actual: "
                        + p5.compareTo(p6)
        );

        // Test case 4: Last name of calling object is
        // lexicographically smaller
        Profile p7 = new Profile(
                "John",
                "Anderson",
                new Date("01/01/2000")
        );
        Profile p8 = new Profile(
                "John",
                "Doe",
                new Date("01/01/2000")
        );
        System.out.println(
                "Test Case 4 - Expected: -1, Actual: "
                        + p7.compareTo(p8)
        );

        // Test case 5: First name of calling object is
        // lexicographically smaller (last names are the same)
        Profile p9 = new Profile(
                "Jane",
                "Doe",
                new Date("01/01/2000")
        );
        Profile p10 = new Profile(
                "John",
                "Doe",
                new Date("01/01/2000")
        );
        System.out.println(
                "Test Case 5 - Expected: -1, Actual: "
                        + p9.compareTo(p10)
        );

        // Test case 6: Date of birth of calling
        // object is earlier when both last and first names are the same
        Profile p11 = new Profile(
                "John",
                "Doe",
                new Date("01/01/2000")
        );
        Profile p12 = new Profile(
                "John",
                "Doe",
                new Date("05/01/2000")
        );
        System.out.println(
                "Test Case 6 - Expected: -1, Actual: "
                        + p11.compareTo(p12)
        );

        // Test case 7: Last name, first name, and date
        // of birth are identical
        Profile p13 = new Profile(
                "John",
                "Smith",
                new Date("01/01/2000")
        );
        Profile p14 = new Profile(
                "John",
                "Smith",
                new Date("01/01/2000")
        );
        System.out.println(
                "Test Case 7 - Expected: 0, Actual: "
                        + p13.compareTo(p14)
        );
    }
}
