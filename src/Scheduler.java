import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the main class that controls the terminal loop.
 * During this loop, user can enter commands to do the following
 * - Schedule appoint, Cancel appoint,
 */
public class Scheduler {
    // initialize list to 4 when we start the program
    private List clinic = new List(4);
    private boolean exited = false;

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
                if (newAppointment != null){
                    clinic.add(newAppointment);
                }
                return;
            case "PP": //
                // placeholder
                return;
            case "C":
                System.out.println("Appointment canceled"); // placeholder
                return;
            case "R":
                System.out.println("Rescheduled appointment"); // placeholder
                return;
            case "PA":
                System.out.println("Displaying appointments, sorted"); // placeholder
                return;
            case "PL":
                System.out.println("Displaying appointments, sorted 1");  // placeholder
                return;
            case "PS":
                System.out.println("Billing statements");
                return;// placeholder
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
}
