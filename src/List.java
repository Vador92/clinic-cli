/**
 * This class manages the Clinic appointments.
 * We can find, add, and remove appointments.
 * We can also increase the size of the number appointments we can add.
 * @author Varun Doreswamy, Yuet Yue
 */
public class List {

    // constants for error handling
    private static final int NOT_FOUND = -1;
    private static final int GROW_SIZE = 4;

    // Instance variables
    private Appointment[] appointments;
    private int size;

    /**
     * This is the constructor for the clinic List.
     * @param size Sets the initial size of the clinic List.
     */
    public List(int size) {
        this.appointments = new Appointment[size];
        this.size = 0;
    }

    /**
     * This method finds an existing appointment in list.
     * @param appointment Is compared to the contents of the list.
     * @return integer of index in the list, or -1 if not found.
     */
    private int find(Appointment appointment) {
        for (int i = 0; i < this.size; i++) {
            //
            if (appointments[i].equals(appointment)) {
                return i; // index
            }
        }
        return NOT_FOUND;
    }

    /**
     * This expands the list by GROW_SIZE (4), if size reaches list length.
     */
    private void grow(){
        //grow thought process
        Appointment[] expandedArray = new Appointment[appointments.length
                + GROW_SIZE];
        // copy contents from original array to new array
        for (int i = 0; i < appointments.length; i++) {
            expandedArray[i] = appointments[i];
        }
        // set appointments to extended array
        appointments = expandedArray;
    }

    /**
     * Swaps the places of two appointments.
     * @param a the first appointment
     * @param b the second appointment.
     */
    private void swap(int a, int b) {
        Appointment temp = appointments[a];
        appointments[a] = appointments[b];
        appointments[b] = temp;
    }

    /**
     * This sorts appointment list by Appointment.
     * Checks in order of:
     * <ol>
     *     <li>Date</li>
     *     <li>Timeslot</li>
     *     <li>Provider Name</li>
     * </ol>
     */
    private void bubbleSortAppointment(Appointment[] appointments) {
        int n = size;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (appointments[j].getDate()
                        .compareTo(appointments[j+1].getDate()) > 0)  {
                    swap(j, j+1);
                }
                else if (appointments[j].getDate()
                        .compareTo(appointments[j+1].getDate()) == 0)  {
                    if (
                            appointments[j].getTimeslot()
                                    .compareTo(appointments[j+1]
                                            .getTimeslot()) > 0
                    ){
                        // problem with swapping
                        swap(j, j+1);
                    }
                    else if (appointments[j].getTimeslot()
                            .compareTo(appointments[j+1]
                                    .getTimeslot()) == 0){
                        if(
                                appointments[j].getProvider()
                                        .name().compareTo(appointments[j+1]
                                                .getProvider().name()) > 0
                        ){
                            swap(j, j+1);
                        }
                    }
                }
            }
        }
    }

    /**
     * This sorts appointment list by Location.
     * Checks in order of:
     * <ol>
     *     <li>County</li>
     *     <li>Date</li>
     *     <li>Timeslot</li>
     * </ol>
     */
    private void bubbleSortLocation(Appointment[] appointments){
        int n = size;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (appointments[j]
                        .getProvider().getLocation()
                        .getCounty().compareTo(appointments[j+1]
                                .getProvider().getLocation()
                                .getCounty()) > 0)  {
                    swap(j, j+1);
                }
                else if (appointments[j].getProvider()
                        .getLocation().getCounty()
                        .compareTo(appointments[j+1].getProvider()
                                .getLocation().getCounty()) == 0)  {
                    if (
                            appointments[j].getDate()
                                    .compareTo(appointments[j+1]
                                            .getDate()) > 0
                    ){
                        // problem with swapping
                        swap(j, j+1);
                    }
                    else if (appointments[j].getDate()
                            .compareTo(appointments[j+1]
                                    .getDate()) == 0){
                        if(
                                appointments[j].getTimeslot()
                                        .compareTo(appointments[j+1]
                                                .getTimeslot()) > 0
                        ){
                            swap(j, j+1);
                        }
                    }
                }
            }
        }
    }

    /**
     * This method finds if an appointment exists within the list.
     * @param appointment Check if appointment is in the list.
     * @return Boolean based on if found or not found.
     */
    public boolean contains(Appointment appointment) {
        return find(appointment) != NOT_FOUND;
    }

    /**
     * Adds a new appointment to the list, if it does not contain it.
     * @param newAppointment Is used to add a new appointment to list.
     */
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

    /**
     * Checks to see if provider is available based on rescheduled appoint.
     * @param checkDate Is date of the appointment being rescheduled
     * @param checkTimeslot Is the timeslot of the existing appointment
     * @param checkProvider Is the provider of the existing appointment
     * @return Boolean to see if the provider is available for reschedule.
     */
    public boolean findProviderAvailability(Date checkDate,
                                            Timeslot checkTimeslot,
                                            Provider checkProvider) {
        for (int i = 0; i < size; i++) {
            if (appointments[i].getDate().equals(checkDate)
                    && appointments[i].getTimeslot().equals(checkTimeslot)
                    && appointments[i].getProvider().equals(checkProvider)) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method is used to check the provider availability of provider.
     * @param newAppointment Appointment which the provider is checked.
     * @return int of the location in the list or -1 if NOT FOUND.
     */
    public int findProviderAvailability(Appointment newAppointment) {
        // need to check if the same date, timeslot, and provided
        for (int i = 0; i < size; i++) {
            if (appointments[i].getDate().equals(newAppointment.getDate())
                    && appointments[i].getTimeslot()
                    .equals(newAppointment.getTimeslot())
                    &&  appointments[i].getProvider()
                    .equals(newAppointment.getProvider())
            ){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Getter method for specific appointment in the list.
     * @param index the index of the appointment.
     * @return Appointment based on the index.
     */
    public Appointment get(int index) {
        return appointments[index];
    }

    /**
     * Method to remove appointment in list, if it exists.
     * @param appointment The appointment that wants to be removed.
     */
    public void remove(Appointment appointment) {
        if(contains(appointment)) {
            int index = find(appointment);
            appointments[index] = appointments[size - 1];
            appointments[size - 1] = null;
            size--;
        }
    }

    /**
     * Getter method for getting the size of the list.
     * @return int of the size of the list.
     */
    public int getSize(){
        return size;
    }

    /**
     * Getter method for the appointments in the clinic List
     * @return Array of Appointments
     */
    public Appointment[] getAppointments() {
        return appointments;
    }

    /**
     * Prints patients sorted by Profile, date, timeslot.
     */
    public void printByPatient() {
        // edge case for empty list
        if(size == 0 ){
            System.out.println("The schedule calendar is empty.");
            return;
        }
        bubbleSortPatient(this.appointments);
        System.out.println("** Appointments ordered " +
                "by patient/date/time **");
        for (int i = 0; i < size; i++) {
            System.out.println(this.appointments[i].toString());
        }
        System.out.println("** end of list **");
    }

    /**
     * Prints appointments in order of county, date, timeslot.
     */
    public void printByLocation(){
        if (size == 0){
            System.out.println("The schedule calendar is empty.");
            return;
        }
        bubbleSortLocation(this.appointments);
        System.out.println("** Appointments ordered by county/date/time **");
        for (int i = 0; i < size; i++){
            System.out.println(appointments[i].toString());
        }
        System.out.println("** end of list **");
    }

    /**
     * Prints the appointments, sorted by date, time, and provider name.
     */
    public void printByAppointment(){
        if(size == 0 ){
            System.out.println("The schedule calendar is empty.");
            return;
        }
        bubbleSortAppointment(this.appointments);
        System.out.println("** Appointments ordered " +
                "by date/time/provider **");
        for (int i = 0; i < size; i++) {
            System.out.println(this.appointments[i].toString());
        }
        System.out.println("** end of list **");
    }

    /**
     * This sorts appointment list by patient.
     * Checks in order of:
     * <ol>
     *     <li>Last Name</li>
     *     <li>First Name</li>
     *     <li>Date of Birth</li>
     *     <li>Date</li>
     *     <li>Timeslot</li>
     * </ol>
     */
    public void bubbleSortPatient(Appointment[] appointments) {
        int n = size;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (appointments[j].getPatientProfile()
                        .compareTo(appointments[j+1]
                                .getPatientProfile()) > 0)  {
                    swap(j, j+1);
                }
                else if (appointments[j].getPatientProfile()
                        .compareTo(appointments[j+1]
                                .getPatientProfile()) == 0)  {
                    if (
                            appointments[j].getDate()
                                    .compareTo(appointments[j+1]
                                            .getDate()) > 0
                    ){
                        // problem with swapping
                        swap(j, j+1);
                    }
                    else if (appointments[j].getDate()
                            .compareTo(appointments[j+1]
                                    .getDate()) == 0){
                        if(
                                appointments[j].getTimeslot()
                                        .compareTo(appointments[j+1]
                                                .getTimeslot()) > 0
                        ){
                            swap(j, j+1);
                        }
                    }
                }
            }
        }
    }
}