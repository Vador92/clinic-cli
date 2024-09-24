import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the main class that controls the terminal loop.
 * During this loop, user can enter commands to do the following
 * - Schedule appoint, Cancel appoint,
 */
public class Scheduler {
    private List clinic = new List();
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
            processCommand(scanner.nextLine(), clinic);
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
    private void processCommand(String input, List clinic){
        // command is always the first section before the comma
        String command;
        if (input.indexOf(',') == -1){
            command = input;
        }
        else{
            command = input.substring(0, input.indexOf(','));
            String arguments = input.substring(1).trim();
        }
        // arguments are anything past the first argument
        switch(command){
            case "Q": // changes exited to true
                exited = true;
                break;
            case "S": // used to schedule an appoint
                System.out.println("Schedule appointment"); // placeholder
                break;
            case "PP": //
                System.out.println("No appointments yet"); // placeholder
                break;
            case "C":
                System.out.println("Appointment canceled"); // placeholder
                break;
            case "R":
                System.out.println("Rescheduled appointment"); // placeholder
                break;
            case "PA":
                System.out.println("Displaying appointments, sorted"); // placeholder
                break;
            case "PL":
                System.out.println("Displaying appointments, sorted 1");  // placeholder
                break;
            case "PS":
                System.out.println("Billing statements"); // placeholder
            default:
                System.out.println("Invalid command!"); // placeholder
        }
    }
}
