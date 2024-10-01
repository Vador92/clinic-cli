/**
 *
 * @author Yuet
 */
// Linked list for the visits
public class Visit {
    private Appointment appointment;
    private Visit next;

    // creates a singular node that will eventually form a singly linked list when more nodes are added
    public Visit(Appointment appointment, Visit next) {
        this.appointment = appointment;
        this.next = next;
    }

    // getter method for the next node in the linked list
    public Visit getNext() {
        return next;
    }

    // setter method to link the next node
    public void setNext(Visit next) {
        this.next = next;
    }

    // getter method for a patient's appointment data
    public Appointment getAppointment () {
        return appointment;
    }

}

