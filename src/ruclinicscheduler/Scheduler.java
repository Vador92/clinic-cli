package ruclinicscheduler;

import java.util.Scanner;

/**
 * This is the Scheduler class, the user's way of controlling appointments.
 * Handles user commands, with read, write, and update.
 * Within the Scheduler, we are able to:
 * 1. Create an appointment, based on provider availability and duplicates
 * 2. Cancel an existing appointment
 * 3. Reschedule an existing appointment, if provider is available
 * 4. Print appointments sorted by patient name, location, or date
 * 5. View billing records for each unique patient
 * @author Varun Doreswamy, Yuet Yue
 */
public class Scheduler {

    // constants
    private static final int NOT_FOUND = -1;
    private static final int EMPTY = 0;
    private static final int STARTING_LIST_SIZE = 4;
    private static final int SOURCE = 0;

    // Private Variables
    private List clinic = new List(STARTING_LIST_SIZE);
    private MedicalRecord records = new MedicalRecord();

    // State Management
    private boolean exited = false;

    // Used to handle each command line
    String command;
    String[] arguments = null;

    /**
     * Creates new Scheduler object, to be used in RunProject1 driver class.
     */
    public Scheduler() {

    }

    /**
     * Process the line by calling getInput
     * Finds the case, depending on command, that is split from arguments.
     * Calls, respective helper function to complete task
     * Does nothing if invalid commands are entered or empty line for ""
     * @param input used to generate command and arguments
     */
    private void processCommand(String input) {
        getInput(input);
        switch(command){
            case "Q":
                exited = true;
                return;
            case "S":
                Appointment newAppointment =
                        Appointment.createAppointment(arguments);
                handleNewAppointment(newAppointment);
                return;
            case "C":
                Appointment cancelAppointment =
                        Appointment.createAppointment(arguments);
                handleCancel(cancelAppointment);
                return;
            case "R":
                handleReschedule();
                return;
            case "PP":
                clinic.printByPatient();
                return;
            case "PA":
                clinic.printByAppointment();
                return;
            case "PL":
                clinic.printByLocation();
                return;
            case "PS":
                handleBillingStatements();
                return;
            case "":
                System.out.println("");
                return;
            default:
                System.out.println("Invalid command!");
        }
    }

    /**
     * This method split the command from the arguments, if arguments exist.
     * Sets the command and arguments to respective global variable.
     * @param input input is separated into command and arguments
     */
    private void getInput(String input){
        if (input.indexOf(',') == NOT_FOUND)
            command = input;
        else{
            command = input.substring(SOURCE, input.indexOf(','));
            String argString = input.substring(
                    input.indexOf(',') + 1).trim();
            arguments = argString.split(",");
        }
    }

    /**
     * This adds a new appointment to clinic, if it exists and is valid.
     * Handles error cases where an appointment cannot be booked.
     * @param newAppointment is used to add new appointment to clinic (List)
     */
    private void handleNewAppointment(Appointment newAppointment) {
        if (newAppointment != null) {
            if (!clinic.contains(newAppointment)) {
                int index = clinic.findProviderAvailability(newAppointment);
                if (index == NOT_FOUND) {
                    clinic.add(newAppointment);
                    System.out.println(newAppointment.toString()
                            + " booked.");
                } else {
                    Appointment existing = clinic.get(index);
                    System.out.println(existing.getProvider().toString()
                            + " is not available at slot "
                            + existing.getTimeslot().getTime()
                            + ".");
                }
            } else {
                System.out.println(
                        newAppointment.getPatientProfile().toString()
                                + " has an existing appointment "
                                + "at the same time slot."
                );
            }
        }
    }

    /**
     * This method cancels an existing appointment (if valid) and in List
     * @param cancelAppointment used to remove appointment from list
     */
    private void handleCancel(Appointment cancelAppointment) {
        if (cancelAppointment != null) {
            int startingSize = clinic.getSize();
            clinic.remove(cancelAppointment);
            if (startingSize > clinic.getSize()) {
                System.out.println(cancelAppointment.getDate().toString()
                        + " "
                        + cancelAppointment.getTimeslot().toString()
                        + " "
                        + cancelAppointment.getPatientProfile().toString()
                        + " has been canceled.");
            }
            else if (startingSize == clinic.getSize()) {
                System.out.println(cancelAppointment.getDate().toString()
                        + " " + cancelAppointment.getTimeslot().toString()
                        + " "
                        + cancelAppointment.getPatientProfile().toString()
                        + " does not exist.");
            }
        }
    }

    /**
     * This method reschedules an appointment based on provider availability
     */
    private void handleReschedule() {
        Date checkDate = new Date(arguments[0]);
        Timeslot checkTimeslot = Timeslot.setTimeslot(arguments[1]);
        Timeslot newTimeslot = Timeslot.setTimeslot(arguments[5]);
        Profile checkProfile = new Profile(arguments[2],
                arguments[3], new Date(arguments[4]));
        for (int i = SOURCE; i < clinic.getSize(); i++) {
            if (clinic.get(i).getDate().equals(checkDate)
                    && clinic.get(i).getTimeslot().equals(checkTimeslot)
                    && clinic.get(i).getPatientProfile().equals(checkProfile)
            ) {
                Provider checkProvider = clinic.get(i).getProvider();
                if (newTimeslot == null) {
                    System.out.println(arguments[5]
                            + " is not a valid time slot.");
                    return;
                }
                if (clinic.findProviderAvailability(
                        checkDate, newTimeslot, checkProvider)) {
                    clinic.remove(clinic.get(i));
                    Appointment rescheduledAppointment
                            = new Appointment(checkDate, newTimeslot,
                            checkProfile, checkProvider);
                    clinic.add(rescheduledAppointment);
                    System.out.println("Rescheduled to "
                            + rescheduledAppointment.toString());
                    return;
                } else {
                    System.out.println(checkProvider.toString()
                            + " is not available at slot "
                            + arguments[5] + ".");
                    return;
                }
            }
        }
        String time = (checkTimeslot == null) ?
                arguments[1] : checkTimeslot.toString();
        System.out.println(checkDate.toString() + " " + time
                + " " + checkProfile.toString() + " does not exist.");
    }

    /**
     * This method shows the total charges for each patient.
     * The order of the patients are by last name.
     */
    private void handleBillingStatements() {
        records.add(clinic);
        if (records.getSize() != EMPTY) {
            System.out.println("** Billing statement ordered by patient **");
            System.out.println(records.toString());
            System.out.println("** end of list **");
        }
        else {
            System.out.println("The schedule calendar is empty.");
        }
    }

    /**
     * The Run method for managing the scheduler terminal program.
     * Here, process command is called for each line entered in terminal.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        // Begin scheduler program
        System.out.println("Scheduler is running.");
        while(!exited)
        {
            // Handle new line from terminal
            processCommand(scanner.nextLine());
        }
        // Exit Scheduler
        System.out.println("Scheduler is terminated.");
    }
}
