import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    @Test
    void exampleLocationTest(){
        Location testLocation = Location.CLARK;

        assertEquals(testLocation, Location.CLARK);

        assertEquals(testLocation.toString(), "Union County,07066");
    }
}