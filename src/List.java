public class List {
    private Appointment[] appointments;
    private int size;

    public List(int size) {
        this.appointments = new Appointment[size];
        this.size = 0;
    }

    // this is to make sure there is no duplicate entry being scheduled
    // this should be ran in create appointment
    private int find(Appointment appointment) {
        for (int i = 0; i < this.size; i++) {
            if (this.appointments[i].equals(appointment)) {
                return 1;
            }
        }
        return -1;
    }

    private void grow(){
        //grow thought process
        Appointment[] expandedArray = new Appointment[appointments.length + 4];
        // copy contents from original array to new array
        System.arraycopy(appointments, 0, expandedArray, 0, appointments.length);
        // set appointments to extended array
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
