import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Varun
 * This class is the main class that controls the terminal loop.
 * During this loop, user can enter commands to do the following
 * - Schedule appoint, Cancel appoint,
 */
public class Scheduler {
    // initialize list to 4 when we start the program
    private List clinic = new List(4);
    private boolean exited = false;

    final static int NOT_FOUND = -1;
    final static int EMPTY = 0;
    final static int PRINT_SHIFT = 1;

    public Scheduler(){} // constructor

    public void run(){
        Scanner scanner = new Scanner(System.in);
        // enter the scheduler
        System.out.println("Scheduler is running.");
        while(!exited) // to satisfy the entry condition
        {
            // get the line, send it over, make sure the first
            // letter is an actual command and then
            //  call the necessary function
            processCommand(scanner.nextLine());
        }
        System.out.println("Scheduler is terminated."); // exit the scheduler
    }

    // check if there is no comma, if not then no substring needed
    /*
    1. make sure no comma
    2. if no comma, split arguments
    3. check command
    4. pass arguments
     */
    private String command;
    private String[] arguments = null;
    private void processCommand(String input){
        getInput(input);
        switch(command){
            case "Q": // changes exited to true
                exited = true;
                return;
            case "S": // used to schedule an appoint
                Appointment newAppointment =
                        Appointment.createAppointment(arguments);
                // handle error handling in appointment
                handleNewAppointment(newAppointment);
                return;
            case "C":
                Appointment cancelAppointment =
                        Appointment.createAppointment(arguments);
                handleCancel(cancelAppointment);
                return;
            case "R":
                // if appointment is null or timeslot is null return
                // takes all but last argument
                handleReschedule();
                return;
            case "PP": //
                clinic.printByPatient();
                return;
            case "PA":
                clinic.printByAppointment();
                return;
            case "PL":
                clinic.printByLocation();  // placeholder
                return;
            case "PS":
                handleBillingStatements();
                return;// placeholder
            case "":
                System.out.println("");
                return;
            default:
                System.out.println("Invalid command!");// placeholder
        }
    }

    // Need to keep this method, in order to get specific commands
    // and arguments needed for each method
    private void getInput(String input){
        if (input.indexOf(',') == -1)
            command = input;
        else{
            command = input.substring(0, input.indexOf(','));
            String argString = input.substring(
                    input.indexOf(',') + 1).trim();
            arguments = argString.split(",");
        }
    }

    //something wrong with the way we handle overbook and duplicate
    private void handleNewAppointment(Appointment newAppointment){
        if (newAppointment != null){
            if(!clinic.contains(newAppointment)){
                int index = clinic.findProviderAvailability(newAppointment);
                if(index == NOT_FOUND){
                    clinic.add(newAppointment);
                    System.out.println(newAppointment.toString() + " booked.");
                }
                else{
                    Appointment existing = clinic.get(index);
                    System.out.println(existing.getProvider().toString()
                            + " has an existing appointment at the same time slot.");
                }
            }
            else{
                System.out.println(newAppointment.getPatientProfile().toString()
                        + " has an existing appointment at the same time slot.");
            }
        }
    }

    private void handleCancel(Appointment cancelAppointment){
        if (cancelAppointment != null){
            int startingSize = clinic.getSize();
            clinic.remove(cancelAppointment);
            if (startingSize > clinic.getSize()){
                System.out.println(cancelAppointment.getDate().toString()
                        + " " + cancelAppointment.getTimeslot().toString()
                        + " " + cancelAppointment.getPatientProfile().toString()
                        + " has been cancelled.");
            }
            else if (startingSize == clinic.getSize()){
                System.out.println(cancelAppointment.getDate().toString()
                        + " " + cancelAppointment.getTimeslot().toString()
                        + " " + cancelAppointment.getPatientProfile().toString()
                        + " does not exist.");
            }
        }
    }

    // fix reschedule
    private void handleReschedule() {
        Date checkDate = new Date(arguments[0]);
        Timeslot checkTimeslot = Timeslot.setTimeslot(arguments[1]);
        Timeslot newTimeslot = Timeslot.setTimeslot(arguments[5]);
        Profile checkProfile = new Profile(arguments[2],
                arguments[3], new Date(arguments[4]));
        if (newTimeslot != null) {
            for (int i = 0; i < clinic.getSize(); i++) {
                if (clinic.get(i).getDate().equals(checkDate)
                        && clinic.get(i).getTimeslot().equals(checkTimeslot)
                        && clinic.get(i).getPatientProfile().equals(checkProfile)) {
                    Provider checkProvider = clinic.get(i).getProvider();
                    if (!clinic.findProviderAvailability(
                            checkDate, checkTimeslot, checkProvider
                    )) {
                        clinic.remove(clinic.get(i));
                        Appointment rescheduledAppointment
                                = new Appointment(
                                checkDate, newTimeslot,
                                checkProfile, checkProvider
                        );
                        clinic.add(rescheduledAppointment);
                        System.out.println("Rescheduled to "
                                + rescheduledAppointment.toString()
                        );
                        return;
                    } else {
                        System.out.println(checkProvider.toString()
                                + " is not available at "
                                + newTimeslot.toString());
                        return;
                    }
                }
            }
            System.out.println(checkDate.toString()
                        + " " + arguments[1]
                        + " " + checkProfile.toString()
                        + " does not exist.");

        } else {
            System.out.println(arguments[5]
                    + " is not a valid time slot."
            );
        }
    }

    private void handleBillingStatements(){
        MedicalRecord records = new MedicalRecord().add(clinic);
        if (records.getSize() != EMPTY){
            System.out.println(records.toString());
        }
        else{
            System.out.println("The schedule calendar is empty.");
        }
    }

}
