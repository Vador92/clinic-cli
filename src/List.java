import java.lang.reflect.AccessibleObject;

/**
     * @author Varun
     */
    public class List {
        private Appointment[] appointments;
        private int size;

        // error handling
        final Integer NOT_FOUND = -1;

        // this is fine
        public List(int size) {
            this.appointments = new Appointment[size];
            this.size = 0;
        }

        // this is to make sure there is no duplicate entry being scheduled
        // this should be ran in create appointment
        // return index
        // this is fine
        private int find(Appointment appointment) {
            for (int i = 0; i < this.size; i++) {
                //
                if (appointments[i].equals(appointment)) {
                    return i; // index
                }
            }
            return NOT_FOUND;
        }

        // this is fine
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

        // this is fine
        // this should check to see if the contents are the same
        // used to block duplicate appointments from being made
        // use find to check if it contains
        public boolean contains(Appointment appointment) {
            return find(appointment) != NOT_FOUND;
        }

        // increase appointments if too big
        // and add new appointment
        public void add(Appointment newAppointment) {
            if (size == appointments.length) {
                grow();
            }
            if (!contains(newAppointment)) {
                // check if same date and time as another provider
                appointments[size] = newAppointment;
                size++;
            }
        }

        public boolean findProviderAvailability(Date checkDate, Timeslot checkTimeslot, Provider checkProvider) {
            for (int i = 0; i < size; i++) {
                if (appointments[i].getDate().equals(checkDate)
                        && appointments[i].getTimeslot().equals(checkTimeslot)
                        && appointments[i].getProvider().equals(checkProvider)) {
                    return false;
                }
            }
            return true;
        }

        public int findProviderAvailability(Appointment newAppointment) {
            // need to check if the same date, timeslot, and provided
            for (int i = 0; i < size; i++) {
                if (appointments[i].getDate().equals(newAppointment.getDate())
                        && appointments[i].getTimeslot().equals(newAppointment.getTimeslot())
                        &&  appointments[i].getProvider().equals(newAppointment.getProvider())
                ){
                    return i;
                }
            }
            return NOT_FOUND;
        }

        public Appointment get(int index) {
            return appointments[index];
        }


        // use the finds method to see if it exists
        // if it does, get the index
        // then remove that
        // then replace the last one using size - 1, with the index
        public void remove(Appointment appointment) {
            if(contains(appointment)) {
                int index = find(appointment);
                appointments[index] = appointments[size - 1];
                appointments[size - 1] = null;
                size--;
            }
        }

        public int getSize(){
            return size;
        }

        // print in order of patients
        public void printByPatient(){
            // edge case for empty list
            if(size == 0 ){
                System.out.println("The schedule calendar is empty.");
                return;
            }
            bubbleSortPatient(this.appointments);
            for (int i = 0; i < size; i++) {
                System.out.println(this.appointments[i].toString());
            }
            System.out.println();

        }


        //
        public void printByLocation(){
            if (size == 0){
                System.out.println("The schedule calendar is empty.");
                return;
            }
            bubbleSortLocation(this.appointments);
            for (int i = 0; i < size; i++){
                System.out.println(appointments[i].toString());
            }
            System.out.println();
        }

        // sort algo for this
        public void printByAppointment(){
            if(size == 0 ){
                System.out.println("The schedule calendar is empty.");
            }
            bubbleSortAppointment(this.appointments);
            for (int i = 0; i < size; i++) {
                System.out.println(this.appointments[i].toString());
            }
            System.out.println();
        }

        private void bubbleSortPatient(Appointment[] appointments) {
            int n = size;
            for (int i = 0; i < n-1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (appointments[j].getPatientProfile().compareTo(appointments[j+1].getPatientProfile()) > 0)  {
                        swap(j, j+1);
                    }
                    else if (appointments[j].getPatientProfile().compareTo(appointments[j+1].getPatientProfile()) == 0)  {
                        if (
                                appointments[j].getDate().compareTo(appointments[j+1].getDate()) > 0
                        ){
                            // problem with swapping
                            swap(j, j+1);
                        }
                        else if (appointments[j].getDate().compareTo(appointments[j+1].getDate()) == 0){
                            if(
                                    appointments[j].getTimeslot().compareTo(appointments[j+1].getTimeslot()) > 0
                            ){
                                swap(j, j+1);
                            }
                        }
                    }
                }
            }
        }


        private void bubbleSortAppointment(Appointment[] appointments) {
            int n = size;
            for (int i = 0; i < n-1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (appointments[j].getDate().compareTo(appointments[j+1].getDate()) > 0)  {
                        swap(j, j+1);
                    }
                    else if (appointments[j].getDate().compareTo(appointments[j+1].getDate()) == 0)  {
                        if (
                                appointments[j].getTimeslot().compareTo(appointments[j+1].getTimeslot()) > 0
                        ){
                            // problem with swapping
                            swap(j, j+1);
                        }
                        else if (appointments[j].getTimeslot().compareTo(appointments[j+1].getTimeslot()) == 0){
                            if(
                                    appointments[j].getProvider().compareTo(appointments[j+1].getProvider()) > 0
                            ){
                                swap(j, j+1);
                            }
                        }
                    }
                }
            }
        }

        private void bubbleSortLocation(Appointment[] appointments){
            int n = size;
            for (int i = 0; i < n-1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (appointments[j].getProvider().getLocation().getCounty().compareTo(appointments[j+1].getProvider().getLocation().getCounty()) > 0)  {
                        swap(j, j+1);
                    }
                    else if (appointments[j].getProvider().getLocation().getCounty().compareTo(appointments[j+1].getProvider().getLocation().getCounty()) == 0)  {
                        if (
                                appointments[j].getDate().compareTo(appointments[j+1].getDate()) > 0
                        ){
                            // problem with swapping
                            swap(j, j+1);
                        }
                        else if (appointments[j].getDate().compareTo(appointments[j+1].getDate()) == 0){
                            if(
                                    appointments[j].getTimeslot().compareTo(appointments[j+1].getTimeslot()) > 0
                            ){
                                swap(j, j+1);
                            }
                        }
                    }
                }
            }
        }

        public void swap(int a, int b) {
            Appointment temp = appointments[a];
            appointments[a] = appointments[b];
            appointments[b] = temp;
        }






    }

    /*
     You can add necessary constants, constructors, and methods. However, you CANNOT change or add
    instance variables. -2 points for each violation.


     You MUST implement and use the methods listed above; you CANNOT change the signatures of the
    methods. -2 points for each violation.

    Fix this
     You CAN use System.out ONLY in the three print() methods listed above.

    This is good
     The find() method searches for an appointment in the list and returns the index if it is found; it returns
    NOT_FOUND if it is not in the list. Define a constant value -1 for NOT_FOUND, or -2 point.

     You must use an “in-place” sorting algorithm to implement the sorting, i.e., the order of the objects in the
    array will be rearranged after the sorting without using an additional array. You CANNOT use
    Arrays.sort() or System.arraycopy() or any other Java library classes or utilities for sorting. You must
    write the code yourself to sort it. You will lose 10 points for the violation.
     */
