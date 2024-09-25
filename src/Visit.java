// Linked list for the visits
public class Visit {
    private Appointment appointment;
    private Visit next;

    public Visit(Appointment appointment, Visit next) {
        this.appointment = appointment;
        this.next = next;
    }

    public Visit getNext() {
        return next;
    }

}

