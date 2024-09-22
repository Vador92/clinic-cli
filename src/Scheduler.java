import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is the main class that controls the terminal loop.
 * During this loop, user can enter commands to do the following
 * - Schedule appoint, Cancel appoint,
 */
public class Scheduler {
    public Scheduler(){} // constructor
    // 37 lines in run, 3 left
    public void run(){
        Scanner scanner = new Scanner(System.in);
        List clinic = new List();
        boolean exited = false;
        while(!exited) // to satisfy the entry condition
        {
            switch(scanner.nextLine()){
                case "Q": // changes exited to true
                    exited = true;
                    break;
                case "S": // used to schedule an appoint
                     // placeholder, replace with function
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
        System.out.println("Scheduler is terminated.");
    }
}
