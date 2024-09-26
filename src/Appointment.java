import java.util.Calendar;

public class Appointment implements Comparable<Appointment> {
    private Date date;
    private Timeslot timeslot;
    private Profile patient;
    private Provider provider;

    // Constructor for making a new appointment object
    public Appointment(Date date, Timeslot timeslot, Profile patient, Provider provider) {
        this.date = date;
        this.timeslot = timeslot;
        this.patient = patient;
        this.provider = provider;
    }

    // create new appointment with checks
    // split the appointment check with the dob check
    public static Appointment createAppointment(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Date appointmentDate = new Date(args[0]);
        Timeslot timeslot = Timeslot.setTimeslot(args[1]);
        Provider provider = Provider.setProvider(args[5]);
        // the if statement below should be a new method
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

    // checks to see if the appointment is valid
    public static boolean isValidAppointment(Calendar calendar, Date appointmentDate) {
        if (appointmentDate.isValid()) {
            //check if today
            if (appointmentDate.isToday(calendar)) {
                System.out.println("Appointment date: "
                        + appointmentDate.toString()
                        + " is today or a date before today.");
                return false;
            }
            // check weekend
            if (appointmentDate.isWeekend(calendar)) {
                System.out.println("Appointment date: " +
                        appointmentDate.toString() +
                        " is Saturday or Sunday.");
                return false;
            }
            // check if date is past 6 month threshold
            if (appointmentDate.isBeyondSixMonths(calendar)) {
                System.out.println("Appointment date: " +
                        appointmentDate.toString() +
                        " is not within six months.");
                return false;
            }
        }
        return true;
    }

    public static boolean isValidDob(Calendar calendar, Date dob) {
        if (!dob.isValid()) {
            if (dob.isToday(calendar) ||
                    dob.isInFuture(calendar)) {
                System.out.println("Patient dob: " +
                        dob.toString() +
                        " is today or a date after today.");
                return false;
            }
        }
        return true;
    }

    public Profile getPatientProfile() {
        return patient;
    }


    // this is the class that equates it fields are the same
    // need to fix this method
    @Override
    public boolean equals(Object obj) {
        // make sure we are checking if we are doing the same obj
        if (this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Appointment appointment = (Appointment) obj;

        // if timeslot and day is same
        return date.equals(appointment.date) && timeslot.equals(appointment.timeslot)
            && patient.equals(appointment.patient) && provider.equals(appointment.provider);
    }

    // Method to convert the object to a readable string in the terminal
    @Override
    public String toString() {
        return String.format("%s %s %s [%s]", date.toString(),
                timeslot.toString(),
                patient.toString(),
                provider.toString());
    }

    // Compare two different Appointments, this is needed for sorting
    @Override
    public int compareTo(Appointment appointment) {
        return 0;
    }

}
