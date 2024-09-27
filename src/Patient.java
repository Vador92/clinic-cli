/**
 * @author Yuet
 */
public class Patient implements Comparable<Patient>{
    private Profile profile;
    private Visit visits;

    public Patient(Profile profile, Visit visits) {
        this.profile = profile;
        this.visits = visits;
    }

    public int charge() {

        // creates a variable that keeps track of all the charges for each
        int totalCharge = 0;
        Visit currentVisit = this.visits;

        while(currentVisit != null) {

            // get the appointment associated with the current visit that is being referenced
            Appointment appointment = currentVisit.getAppointment();

            Provider provider = appointment.getProvider();

            Specialty specialty = provider.getSpecialty();

            // adds to the total charge to an appointment after it is completed
            totalCharge += provider.getSpecialty().getCharge();


            // moves to the next appointment node
            currentVisit = currentVisit.getNext();
        }

        return totalCharge;
    }
    // create method to return the visit charge for the patient's visit

    @Override
    public String toString() {
        return profile + " [amount due: $" + charge() + "]";
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Patient other = (Patient) object;
        return this.profile.equals(other.profile);
    }

    // need to complete this
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