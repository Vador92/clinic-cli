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

    //
    public static Appointment createAppointment(String[] args){
        Calendar calendar = Calendar.getInstance();
       Date appointmentDate = new Date(args[0]);
       Timeslot timeslot = Timeslot.setTimeslot(args[1]);
       Provider provider = Provider.setProvider(args[5]);
       // the if statement below should be a new method
       if (appointmentDate.isValid()){
           //check if today
           if(appointmentDate.isToday(calendar)) {
               System.out.println("Appointment date: "
                       + appointmentDate.toString()
                       + " is today or a date before today.");
               return null;
           }
           // check weekend
           if(appointmentDate.isWeekend(calendar)){
               System.out.println("Appointment date: "+
                       appointmentDate.toString() +
                       " is Saturday or Sunday.");
               return null;
           }
           if(appointmentDate.isBeyondSixMonths(calendar)){
               System.out.println("Appointment date: "+
                       appointmentDate.toString()+
                       " is not within six months.");
               return null;
           }
       }
       Date dob = new Date(args[4]);
       if (!dob.isValid()){
           if(dob.isToday(calendar) ||
                dob.isInFuture(calendar)) {
               System.out.println("Patient dob: "+
                       dob.toString()+
                       " is today or a date after today.");
               return null;
           }
       }
       if (timeslot == null){
           System.out.println(args[1] + " is not a valid time slot.");
           return null;
       }
       if (provider == null){
           System.out.println(args[5] + " - provider doesn't exist.");
           return null;
       }
       Profile patient = new Profile(args[2], args[3], dob);

        return new Appointment(appointmentDate, timeslot, patient, provider);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        return false;
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
