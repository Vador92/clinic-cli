import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PatientTest {

    private Profile profile1;
    private Profile profile2;
    private Visit visit1;
    private Visit visit2;
    private Patient patient1;
    private Patient patient2;
    private Patient patient3;
    private Provider provider1;
    private Provider provider2;

    @BeforeEach
    void setUp() {
        // Assuming Profile, Visit, Appointment, Provider, and Specialty classes are well-defined

        profile1 = new Profile("John", "Doe", new Date("01/01/1980"));
        profile2 = new Profile("Jane", "Smith", new Date("02/02/1990"));

        // Set up the provider and specialty charges
        provider1 = Provider.HARPER;  // Assume HARPER is FAMILY with a charge of 250
        provider2 = Provider.TAYLOR;  // Assume TAYLOR is PEDIATRICIAN with a charge of 300

        // Set up appointments for visits
        Appointment appointment1 = new Appointment(new Date("09/30/2024"), Timeslot.SLOT1, profile1, provider1);  // Appointment with HARPER
        Appointment appointment2 = new Appointment(new Date("10/25/2024"), Timeslot.SLOT1, profile2, provider2);  // Appointment with TAYLOR

        // Create linked visits
        visit1 = new Visit(appointment1, null);  // Single visit
        visit2 = new Visit(appointment2, visit1);  // Another visit linked to the previous

        // Create patients with their visits
        patient1 = new Patient(profile1, visit2);  // Patient with two visits
        patient2 = new Patient(profile1, visit2);  // Another patient with the same profile and visits
        patient3 = new Patient(profile2, visit1);  // Patient with a different profile and one visit
    }

    @Test
    void testCharge_MultipleVisits() {
        // Test total charge with two visits: HARPER (250) + TAYLOR (300) = 550
        assertEquals(550, patient1.charge());
    }

    @Test
    void testCharge_SingleVisit() {
        // Test total charge with one visit: HARPER (250)
        assertEquals(250, patient3.charge());
    }

    @Test
    void testEquals_SameObject() {
        // Test that a Patient object equals itself
        assertTrue(patient1.equals(patient1));
    }

    @Test
    void testEquals_SameProfile() {
        // Test that two Patient objects with the same profile are equal
        assertTrue(patient1.equals(patient2));
    }

    @Test
    void testEquals_DifferentProfile() {
        // Test that two Patient objects with different profiles are not equal
        assertFalse(patient1.equals(patient3));
    }

    @Test
    void testToString() {
        // Test the toString method for patient1 with charge 300
        assertEquals("John Doe 1/1/1980 [amount due: $300]", patient1.toString());
    }

    @Test
    void testCompareTo_LargerCharge() {
        // Test compareTo when one patient has a larger charge
        assertTrue(patient1.compareTo(patient3) > 0);  // patient1 has 550, patient3 has 250
    }

    @Test
    void testCompareTo_EqualCharge() {
        // Test compareTo when two patients have the same charge
        assertEquals(0, patient1.compareTo(patient2));  // Both patients have a charge of 550
    }

    @Test
    void testCompareTo_SmallerCharge() {
        // Test compareTo when one patient has a smaller charge
        assertTrue(patient3.compareTo(patient1) < 0);  // patient3 has 250, patient1 has 550
    }
}
