import java.text.DecimalFormat;

/**
 * This is the Patient class which manages the patient's information
 * The information in question is the profile and associated visits
 * @author Yuet Yue
 */
public class Patient implements Comparable<Patient>{

    // instance variables
    private Profile profile;
    private Visit visits;

    /**
     * This the constructor method for creating patients
     * @param profile used for patient full name and date of birth
     * @param visits used for the tracking of the patient's visit history
     */
    public Patient(Profile profile, Visit visits) {
        this.profile = profile;
        this.visits = visits;
    }

    /**
     * This method adds an appointment to the patient object
     * @param newAppointment used for specified appointment adding
     */
    public void addAppointment(Appointment newAppointment) {
        Visit newVisit = new Visit(newAppointment, null);

        if (this.visits == null){
            this.visits = newVisit;
        }
        else {
            Visit currentVisit = this.visits;
            while(currentVisit.getNext() != null){
                currentVisit = currentVisit.getNext();
            }
            currentVisit.setNext(newVisit);
        }
    }

    /**
     * This method calculates the total charge for all visits
     * Each visit charge is calculated based on the provider's specialty
     * @return the total charge that the patient owes
     */
    public int charge() {
        int totalCharge = 0;
        Visit currentVisit = this.visits;
        while(currentVisit != null) {
            Appointment appointment = currentVisit.getAppointment();
            Provider provider = appointment.getProvider();
            totalCharge += provider.getSpecialty().getCharge();
            currentVisit = currentVisit.getNext();
        }
        return totalCharge;
    }

    /**
     * This method acts as a getter method for the profile of the patient
     * @return profile object of the patient
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * This method acts as a getter method for charging the patient's visits
     * @return the total charge that the patient owes
     */
    public int getCharge() {
        return charge();
    }

    /**
     * This method acts as a getter method for the visits that a patient has
     * @return the visits that a patient has booked
     */
    public Visit getVisits() {
        return visits;
    }

    /**
     * This method the billing statement for a patient in proper format
     * @return String format of the patient's billing records
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        return profile.toString()
                + " [amount due: $" + df.format(getCharge()) + "]";
    }

    /**
     * This method checks for duplicate patient objects
     * @param object to compare another patient object for duplicate checking
     * @return TRUE if the patient is a duplicate, FALSE if it is different
     */
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

    /**
     * This method compares the values within patient for sorting purposes
     * @param otherPatient used to compare with the other profile and visits
     * @return -1, 1, or 0 if the charge is lesser, greater, or equal to
     */
    @Override
    public int compareTo(Patient otherPatient) {
        return Integer.compare(this.charge(), otherPatient.charge());
    }
}