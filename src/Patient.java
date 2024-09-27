/**
 * @author Yuet
 */
public class Patient implements Comparable<Patient>{
    private Profile profile;
    private Visit visits;

    public Patient() {
    }

    public int charge() {
        return 0;
    }
    // create method to return the visit charge for the patient's visit

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }

    @Override
    public int compareTo(Patient otherPatient) {
        // For example, compare by visit charge
        return Integer.compare(this.charge(), otherPatient.charge());
    }

}


/*
 You can add necessary constants, constructors, and methods; however, you CANNOT change or add the
instance variables, or -2 points for each violation.
 You must override the equals(), toString() and compareTo(), with the @Override tag, or -2 points for
each violation.
 The charge() method traverses the linked list “visits” and returns the charge of the patient, -3 points if
this method is missing.
 */