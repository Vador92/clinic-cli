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
        //grow thought process
        Appointment[] expandedArray = new Appointment[appointments.length + 4];
        System.arraycopy(appointments, 0, expandedArray, 0, appointments.length);
        appointments = expandedArray;
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
        appointments[size] = appointment;
        size++;
        System.out.println(appointment.toString() + " booked.");
    }

    public void remove(Appointment appointment) {

    }

    // sort algo for this
    public void printByPatient(){

    }
    // sort algo for this
    public void printByAppointment(){

    }


}
