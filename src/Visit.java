
/**
 * This is the Visit class which tracks a patient's visits
 * The individual patient's visits are stored within a linked list
 * @author Yuet Yue
 */
public class Visit {

    // instance variables
    private Appointment appointment;
    private Visit next;

    /**
     * This method creates a node object that represents a patient's visit
     * @param appointment used to contain the date information of appointment
     * @param next returns the node address that contains the next visit
     */
    public Visit(Appointment appointment, Visit next) {
        this.appointment = appointment;
        this.next = next;
    }

    /**
     * This method acts as a getter for the next node in visit tracking
     * The next node contains another appointment's date
     * @return the address of the next node
     */
    public Visit getNext() {
        return next;
    }

    /**
     * This method acts as a getter for the appointment that is being added
     * The appointment's date information will be added to the linked list
     * @return the appointment date that is being added into visit tracking
     */
    public Appointment getAppointment () {
        return appointment;
    }

    /**
     * This method acts as a setter to link the current node to the next node
     * The next node's address gets referenced in the current node
     * @param next used as the address of the next node containing next visit
     */
    public void setNext(Visit next) {
        this.next = next;
    }
}

