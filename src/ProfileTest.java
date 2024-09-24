import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void testEquals_SameProfile() {
        // Arrange
        Date dob = new Date(1, 1, 1990);
        Profile profile1 = new Profile("John", "Doe", dob);
        Profile profile2 = new Profile("John", "Doe", dob);

        // Act & Assert
        assertTrue(profile1.equals(profile2));
    }

    @Test
    void testEquals_DifferentFirstName() {
        // Arrange
        Date dob = new Date(1, 1, 1990);
        Profile profile1 = new Profile("John", "Doe", dob);
        Profile profile2 = new Profile("Jane", "Doe", dob);

        // Act & Assert
        assertFalse(profile1.equals(profile2));
    }

    @Test
    void testEquals_DifferentLastName() {
        // Arrange
        Date dob = new Date(1, 1, 1990);
        Profile profile1 = new Profile("John", "Doe", dob);
        Profile profile2 = new Profile("John", "Smith", dob);

        // Act & Assert
        assertFalse(profile1.equals(profile2));
    }

    @Test
    void testEquals_DifferentDob() {
        // Arrange
        Date dob1 = new Date(1, 1, 1990);
        Date dob2 = new Date(2, 2, 1991);
        Profile profile1 = new Profile("John", "Doe", dob1);
        Profile profile2 = new Profile("John", "Doe", dob2);

        // Act & Assert
        assertFalse(profile1.equals(profile2));
    }

    @Test
    void testEquals_NullFields() {
        // Arrange
        Date dob = new Date(1, 1, 1990);
        Profile profile1 = new Profile(null, "Doe", dob);
        Profile profile2 = new Profile("John", null, dob);

        // Act & Assert
        assertFalse(profile1.equals(profile2));
        assertFalse(profile2.equals(profile1));
    }

    @Test
    void testCompareTo_SameProfile() {
        // Arrange
        Date dob = new Date(1, 1, 1990);
        Profile profile1 = new Profile("John", "Doe", dob);
        Profile profile2 = new Profile("John", "Doe", dob);

        // Act & Assert
        assertEquals(0, profile1.compareTo(profile2));
    }

    @Test
    void testCompareTo_DifferentLastName() {
        // Arrange
        Date dob = new Date(1, 1, 1990);
        Profile profile1 = new Profile("John", "Doe", dob);
        Profile profile2 = new Profile("John", "Smith", dob);

        // Act & Assert
        assertTrue(profile1.compareTo(profile2) < 0);  // "Doe" comes before "Smith"
    }

    @Test
    void testCompareTo_DifferentFirstName() {
        // Arrange
        Date dob = new Date(1, 1, 1990);
        Profile profile1 = new Profile("John", "Doe", dob);
        Profile profile2 = new Profile("Jane", "Doe", dob);

        // Act & Assert
        assertTrue(profile1.compareTo(profile2) > 0);  // "John" comes after "Jane"
    }

    @Test
    void testCompareTo_DifferentDob() {
        // Arrange
        Date dob1 = new Date(1, 1, 1990);
        Date dob2 = new Date(2, 2, 1991);
        Profile profile1 = new Profile("John", "Doe", dob1);
        Profile profile2 = new Profile("John", "Doe", dob2);

        // Act & Assert
        assertTrue(profile1.compareTo(profile2) < 0);  // dob1 is earlier than dob2
    }

    @Test
    void testToString() {
        // Arrange
        Date dob = new Date(1, 1, 1990);
        Profile profile = new Profile("John", "Doe", dob);

        // Act & Assert
        assertEquals("John Doe 1/1/1990", profile.toString());
    }
}
