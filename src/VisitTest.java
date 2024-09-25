import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VisitTest {

    private Appointment appointment1;
    private Appointment appointment2;
    private Visit visit1;
    private Visit visit2;

    // Initialize some appointments and visits before each test
    @BeforeEach
    void setUp() {
        // Assuming Appointment class has a constructor like this:
        appointment1 = new Appointment(new Date("9/31/2024"), Timeslot.SLOT1, new Profile("John","Doe", new Date("12/13/1989")), Provider.PATEL);
        appointment2 = new Appointment(new Date("11/2/2024"), Timeslot.SLOT1, new Profile("John","Doe", new Date("12/13/1989")), Provider.PATEL);

        visit1 = new Visit(appointment1, null);
        visit2 = new Visit(appointment2, null);
    }

    @Test
    void testVisitConstructor() {
        // Test the constructor and if the Appointment is stored correctly
        assertEquals(appointment1, visit1.getAppointment(), "The appointment for visit1 should be appointment1");
        assertNull(visit1.getNext(), "The next visit for visit1 should be null initially");
    }

    @Test
    void testSetNextVisit() {
        // Test linking visit1 to visit2
        visit1.setNext(visit2);
        assertEquals(visit2, visit1.getNext(), "visit1's next node should be visit2");
    }

    @Test
    void testGetAppointment() {
        // Test retrieving the appointment from a visit
        assertEquals(appointment2, visit2.getAppointment(), "The appointment for visit2 should be appointment2");
    }

    @Test
    void testGetNextVisit() {
        // Test getting the next visit after setting it
        visit1.setNext(visit2);
        assertEquals(visit2, visit1.getNext(), "visit1's next node should be visit2");
        assertNull(visit2.getNext(), "visit2's next node should be null");
    }
}
