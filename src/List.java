public class List {
    private Appointment[] appointments;
    private int size;

    // error handling
    final static Integer NOT_FOUND = -1;

    public List(int size) {
        this.appointments = new Appointment[size];
        this.size = 0;
    }

    // this is to make sure there is no duplicate entry being scheduled
    // this should be ran in create appointment
    // return index
    private int find(Appointment appointment) {
        for (int i = 0; i < this.size; i++) {
            //
            if (appointments[i].equals(appointment)) {
                return i; // index
            }
        }
        return NOT_FOUND;
    }

    private void grow(){
        //grow thought process
        Appointment[] expandedArray = new Appointment[appointments.length + 4];
        // copy contents from original array to new array
        for (int i = 0; i < appointments.length; i++) {
            expandedArray[i] = appointments[i];
        }
        // set appointments to extended array
        appointments = expandedArray;
    }

    // this should check to see if the contents are the same
    // used to block duplicate appointments from being made
    // use find to check if it contains
    public boolean contains(Appointment appointment) {
        return find(appointment) != NOT_FOUND;
    }

    // increase appointments if too big
    // and add new appointment
    public void add(Appointment appointment) {
        if (size == appointments.length) {
            grow();
        }
        // check duplicate
        if (!contains(appointment)) {
            appointments[size] = appointment;
            size++;
            System.out.println(appointment.toString() + " booked.");
        }
        else{
            Appointment duplicate = appointments[find(appointment)];
            Profile dupProfile = duplicate.getPatientProfile();
            System.out.println(dupProfile.toString()+ " has existing appoint at timeslot");
        }
    }

    // use the finds method to see if it exists
    // if it does, get the index
    // then remove that
    // then replace the last one using size - 1, with the index
    // need to make sure it exits when calling the command
    public void remove(Appointment appointment) {
        if(!contains(appointment)) {
            // should print date, timeslot, profile
            System.out.println(appointment.getDate().toString()
                    + " " + appointment.getTimeslot().toString()
                    + " " + appointment.getPatientProfile().toString()
                    + " not found.");
        }
        else{
            int index = find(appointment);
            appointments[index] = appointments[size - 1];
            appointments[size - 1] = null;
            size--;
            System.out.println(appointment.getDate().toString()
                    + " " + appointment.getTimeslot().toString()
                    + " " + appointment.getPatientProfile().toString()
                    + " has been canceled.");
        }
    }

    // sort algo for this
    public void printByPatient(){

    }
    // sort algo for this
    public void printByAppointment(){

    }


}
