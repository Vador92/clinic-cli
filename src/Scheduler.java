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
                // once completely valid, we create one
                Appointment newAppointment =
                        Appointment.createAppointment(arguments);
                // handle error handling in appointment
                // make this a new function
                handleNewAppointment(newAppointment);
                return;
            case "C":
                Appointment cancelAppointment =
                        Appointment.createAppointment(arguments);
                if (cancelAppointment != null){
                    clinic.remove(cancelAppointment);
                }
                return;
            case "R":
                // if appointment is null or timeslot is null return
                // takes all but last argument
                String[] oldArgs = new String[arguments.length-1];
                for (int i = 0; i < oldArgs.length; i++){
                    oldArgs[i] = arguments[i];
                }
                Appointment rescheduleAppointment =
                        Appointment.createAppointment(oldArgs);
                Timeslot newtime = Timeslot.setTimeslot(arguments[arguments.length-1]);
                if (rescheduleAppointment != null && newtime != null){

                }
                // !null and !null
                System.out.println("Rescheduled appointment"); // placeholder
                return;
            case "PP": //
                clinic.printByPatient();
                return;
            case "PA":
                clinic.printByAppointment(); // placeholder
                return;
            case "PL":
                clinic.printByLocation();  // placeholder
                return;
            case "PS":
                System.out.println("Billing statements");
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

    //
    private void handleNewAppointment(Appointment newAppointment){
        if (newAppointment != null && !clinic.contains(newAppointment)){
            int index = clinic.checkProviderAvailability(newAppointment);
            if(index == NOT_FOUND){
                clinic.add(newAppointment);
                System.out.println(newAppointment.toString() + " booked.");
            }
            else{
                Appointment existing = clinic.get(index);
                System.out.println(existing.getPatientProfile().toString()
                        + " has an existing appointment at the same time slot.");
            }
        }
    }
}
