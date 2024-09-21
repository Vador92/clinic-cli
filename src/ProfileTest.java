import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {
    @Test
    void testProfile() {
        Date dob = new Date(04,20, 2003);
        Profile profile = new Profile("Varun", "Doreswamy", dob);

        assertEquals(profile.toString(), "Varun Doreswamy 04/20/2003");
    }
}