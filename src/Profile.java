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
        int lnameCompare = lname.compareToIgnoreCase(other.lname); //checks for duplicate lnames
        if (lnameCompare != 0) {
            return lnameCompare; //if duplicates not found return lname
        }

        int fnameCompare = fname.compareToIgnoreCase(other.fname);
        if (fnameCompare != 0) {
            return fnameCompare;
        }

        return dob.compareTo(other.dob);
    }


    public static void main(String[] args) {
        testEquals_SameProfile();
        testEquals_DifferentFirstName();
        testEquals_DifferentLastName();
        testEquals_DifferentDob();
        testEquals_NullFields();
        testCompareTo_SameProfile();
        testCompareTo_DifferentLastName();
        testCompareTo_DifferentFirstName();
        testCompareTo_DifferentDob();
        testToString();
    }

    private static void testEquals_SameProfile() {
        Date dob = new Date("01/01/1990");
        Profile profile1 = new Profile("John", "Doe", dob);
        Profile profile2 = new Profile("John", "Doe", dob);

        if (profile1.equals(profile2)) {
            System.out.println("testEquals_SameProfile: Passed");
        } else {
            System.out.println("testEquals_SameProfile: Failed");
        }
    }

    private static void testEquals_DifferentFirstName() {
        Date dob = new Date("01/01/1990");
        Profile profile1 = new Profile("John", "Doe", dob);
        Profile profile2 = new Profile("Jane", "Doe", dob);

        if (!profile1.equals(profile2)) {
            System.out.println("testEquals_DifferentFirstName: Passed");
        } else {
            System.out.println("testEquals_DifferentFirstName: Failed");
        }
    }

    private static void testEquals_DifferentLastName() {
        Date dob = new Date("01/01/1990");
        Profile profile1 = new Profile("John", "Doe", dob);
        Profile profile2 = new Profile("John", "Smith", dob);

        if (!profile1.equals(profile2)) {
            System.out.println("testEquals_DifferentLastName: Passed");
        } else {
            System.out.println("testEquals_DifferentLastName: Failed");
        }
    }

    private static void testEquals_DifferentDob() {
        Date dob1 = new Date("01/01/1990");
        Date dob2 = new Date("02/02/1991");
        Profile profile1 = new Profile("John", "Doe", dob1);
        Profile profile2 = new Profile("John", "Doe", dob2);

        if (!profile1.equals(profile2)) {
            System.out.println("testEquals_DifferentDob: Passed");
        } else {
            System.out.println("testEquals_DifferentDob: Failed");
        }
    }

    private static void testEquals_NullFields() {
        Date dob = new Date("01/01/1990");
        Profile profile1 = new Profile(null, "Doe", dob);
        Profile profile2 = new Profile("John", null, dob);

        if (!profile1.equals(profile2) && !profile2.equals(profile1)) {
            System.out.println("testEquals_NullFields: Passed");
        } else {
            System.out.println("testEquals_NullFields: Failed");
        }
    }

    private static void testCompareTo_SameProfile() {
        Date dob = new Date("01/01/1990");
        Profile profile1 = new Profile("John", "Doe", dob);
        Profile profile2 = new Profile("John", "Doe", dob);

        if (profile1.compareTo(profile2) == 0) {
            System.out.println("testCompareTo_SameProfile: Passed");
        } else {
            System.out.println("testCompareTo_SameProfile: Failed");
        }
    }

    private static void testCompareTo_DifferentLastName() {
        Date dob = new Date("01/01/1990");
        Profile profile1 = new Profile("John", "Doe", dob);
        Profile profile2 = new Profile("John", "Smith", dob);

        if (profile1.compareTo(profile2) < 0) {
            System.out.println("testCompareTo_DifferentLastName: Passed");
        } else {
            System.out.println("testCompareTo_DifferentLastName: Failed");
        }
    }

    private static void testCompareTo_DifferentFirstName() {
        Date dob = new Date("01/01/1990");
        Profile profile1 = new Profile("John", "Doe", dob);
        Profile profile2 = new Profile("Jane", "Doe", dob);

        if (profile1.compareTo(profile2) > 0) {
            System.out.println("testCompareTo_DifferentFirstName: Passed");
        } else {
            System.out.println("testCompareTo_DifferentFirstName: Failed");
        }
    }

    private static void testCompareTo_DifferentDob() {
        Date dob1 = new Date("01/01/1990");
        Date dob2 = new Date("02/02/1991");
        Profile profile1 = new Profile("John", "Doe", dob1);
        Profile profile2 = new Profile("John", "Doe", dob2);

        if (profile1.compareTo(profile2) < 0) {
            System.out.println("testCompareTo_DifferentDob: Passed");
        } else {
            System.out.println("testCompareTo_DifferentDob: Failed");
        }
    }

    private static void testToString() {
        Date dob = new Date("01/01/1990");
        Profile profile = new Profile("John", "Doe", dob);

        if (profile.toString().equals("John Doe 1/1/1990")) {
            System.out.println("testToString: Passed");
        } else {
            System.out.println("testToString: Failed");
        }
    }
}
