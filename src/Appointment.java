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


    // Method to convert the object to a readable string in the terminal
    @Override
    public String toString() {
        return "";
    }

    // Compare two different Appointments, this is needed for sorting
    @Override
    public int compareTo(Appointment appointment) {
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        return true;
    }
}
