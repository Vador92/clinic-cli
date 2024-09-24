public class List {
    private Appointment[] appointments;
    private int size;

    public List(int size) {
        this.appointments = new Appointment[size];
        this.size = 0;
    }

    private int find(Appointment appointment) {
        return 0;
    }

    private void grow(){

    }

    public boolean contains(Appointment appointment) {
        return false;
    }

    // increase appointments if too big
    // and add new appointment
    public void add(Appointment appointment) {
        if (size == appointments.length) {
            grow();
        }
        appointments[size++] = appointment;
        System.out.println(appointment.toString() + " booked.");
    }

    public void remove(Appointment appointment) {

    }

    public void printByPatient(){

    }

    public void printByAppointment(){

    }


}
