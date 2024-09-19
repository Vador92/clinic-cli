import java.util.Scanner;

public class Scheduler {
    public Scheduler(){

    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        boolean exited = false;
        while(!exited) // to satisfy the entry condition
        {
            switch(scanner.nextLine()){
                case "Q":
                    exited = true;
                    break;
                case "PP":
                    System.out.println("No appointments yet");
                    break;
                default:
                    System.out.println("Invalid appointment");
            }
        }
        System.out.println("Scheduler is terminated.");
    }
}
