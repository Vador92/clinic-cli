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
                break;
            case "S": // used to schedule an appoint
                try{
                    clinic.add(new Appointment(getDate(arguments[0]),
                                    getTimeslot(arguments[1]),
                                    getProfile(arguments[2], arguments[3], arguments[4]),
                                    Provider.setProvider(arguments[5])
                            )
                    );
                }
                catch(Exception e){System.out.println(e);}
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

    private Timeslot getTimeslot(String timeslot){
        return switch (timeslot){
            case "1" -> Timeslot.SLOT1;
            case "2" -> Timeslot.SLOT2;
            case "3" -> Timeslot.SLOT3;
            case "4" -> Timeslot.SLOT4;
            case "5" -> Timeslot.SLOT5;
            case "6" -> Timeslot.SLOT6;
            default -> null;
        };
    }

    private Date getDate(String date){
        try{
            return new Date(date);
        }
        catch(Exception e){
            return null;
        }
    }

    private Profile getProfile(String fname, String lname, String dob){
        try{
            return new Profile(fname,lname, new Date(dob));
        }
        catch(Exception e){
            return null;
        }
    }

    private void getInput(String input){
        if (input.indexOf(',') == -1)
            command = input;
        else{
            command = input.substring(0, input.indexOf(','));
            String argString = input.substring(input.indexOf(',') + 1).trim();
            arguments = argString.split(",");
        }
    }
}
