import java.util.Scanner;

public class Scheduler {
    public Scheduler(){} // constructor

    public void run(){
        Scanner scanner = new Scanner(System.in);
        boolean exited = false;
        while(!exited) // to satisfy the entry condition
        {
            switch(scanner.nextLine()){
                case "Q":
                    exited = true;
                    break;
                case "S":
                    System.out.println("Schedule appointment");  // placeholder
                    break;
                case "PP":
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
                    System.out.println("Invalid appointment"); // placeholder
            }
        }
        System.out.println("Scheduler is terminated.");
    }
}
