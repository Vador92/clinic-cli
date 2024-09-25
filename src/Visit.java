import java.util.LinkedList;

// Linked list for the visits
public class Visit {
    private Appointment appointment;
    private Visit next;

    public Visit(Appointment appointment, Visit next) {
        this.appointment = appointment;
        this.next = next;
    }

    // method for adding visits that a patient has scheduled
    public void add() {

    }

    // method for adding a vist that occurs before other visits
    public void addFirst() {

    }


    public Visit getNext() {
        return next;
    }

}

