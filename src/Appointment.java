import java.util.Calendar;

/**
 * This class is for creating, validating, and formatting an appointment.
 * @author Varun Doreswamy, Yuet Yue
 */
public class Appointment implements Comparable<Appointment> {

    // instance variables
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

    /**
     * This is the constructor of the appointment object, with 4 parameters.
     * @param date THis is the date of the appointment
     * @param timeslot The time of the day
     * @param patient The patient the appointment is associated with
     * @param provider The provider which is being seeked by patient.
     */
    public Appointment(
            Date date,
            Timeslot timeslot,
            Profile patient,
            Provider provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    /**
     * This method creates a new appointment, checking its validity.
     * @return A new appointment based on if all checks are good.
     */
    public static Appointment createAppointment(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Date appointmentDate = new Date(args[0]);
        Timeslot timeslot = Timeslot.setTimeslot(args[1]);
        Provider provider = Provider.setProvider(args[5]);
        if (!isValidAppointment(calendar, appointmentDate)) {
            return null;
        }
        Date dob = new Date(args[4]);
        if (!isValidDob(calendar, dob)) {
            return null;
        }
        if (timeslot == null) {
            System.out.println(args[1] + " is not a valid time slot.");
            return null;
        }
        if (provider == null) {
            System.out.println(args[5] + " - provider doesn't exist.");
            return null;
        }
        Profile patient = new Profile(args[2], args[3], dob);

        return new Appointment(appointmentDate, timeslot, patient, provider);
    }

    /**
     * This method checks the validity of the appointment.
     * It checks the appointment date, to see if valid, today, or in the past
     * @param calendar The instance of a calendar used to check conditions
     * @param appointmentDate Uses the date to compare with Calendar object
     * @return Boolean of whether appointment is valid or not.
     */
    public static boolean isValidAppointment(Calendar calendar,
                                             Date appointmentDate) {
        if (!appointmentDate.isValid()) {
            System.out.println("Appointment date: "
                    + appointmentDate.toString()
                    + " is not a valid calendar date.");
            return false;
        }
        if (appointmentDate.isToday(calendar) ||
                appointmentDate.isBeforeToday(calendar)) {
            System.out.println("Appointment date: "
                    + appointmentDate.toString()
                    + " is today or a date before today.");
            return false;
        }
        if (appointmentDate.isWeekend(calendar)) {
            System.out.println("Appointment date: " +
                    appointmentDate.toString() +
                    " is Saturday or Sunday.");
            return false;
        }
        if (appointmentDate.isBeyondSixMonths(calendar)) {
            System.out.println("Appointment date: " +
                    appointmentDate.toString() +
                    " is not within six months.");
            return false;
        }
        return true;
    }

    /**
     * Method to check if the dob of Patient is valid.
     * @param calendar Uses calendar object to compare with dob
     * @param dob The dob of the patient
     * @return Boolean of whether all conditions pass.
     */
    public static boolean isValidDob(Calendar calendar, Date dob) {
        if (dob.isValid()) {
            if (dob.isToday(calendar) ||
                    dob.isInFuture(calendar)) {
                System.out.println("Patient dob: " +
                        dob.toString() +
                        " is today or a date after today.");
                return false;
            }
            return true;
        }
        System.out.println("Patient dob: " +
                dob.toString() +
                " is not a valid calendar date.");
        return false;
    }

    /**
     * Method to get the patient profile.
     * @return Profile tied to the appointment
     */
    public Profile getPatientProfile() {
        return patient;
    }

    /**
     * Method to get the date of the appointment.
     * @return Date of the tied appointment
     */
    public Date getDate(){
        return date;
    }

    /**
     * Method to get the Timeslot of the appointment.
     * @return Timeslot of the tied appointment.
     */
    public Timeslot getTimeslot() {
        return timeslot;
    }

    /**
     * Method to get the Provider of the appointment.
     * @return Provider of the tied appointment.
     */
    public Provider getProvider(){
        return provider;
    }

    /**
     * Method to compare if two appointments are equal.
     * Two appointments are equal if same date, timeslot, and profile.
     * @param obj the appointment that is being compared to one calling it.
     * @return Boolean of whether the two appointments are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Appointment appointment = (Appointment) obj;

        return date.equals(appointment.date) &&
                timeslot.equals(appointment.timeslot)
                && patient.equals(appointment.patient);
    }

    /**
     * This method returns a formatted String of the appointment
     * @return String of the appointment.
     */
    @Override
    public String toString() {
        return String.format("%s %s %s %s", date.toString(),
                timeslot.toString(),
                patient.toString(),
                provider.toString());
    }

    /**
     * Compares two appointments, not used because checks are specific.
     * @param appointment the object to be compared.
     * @return int for whether it is greater, equal, or less than.
     */
    @Override
    public int compareTo(Appointment appointment) {
        return 0;
    }
}