import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    @Test
    void exampleLocationTest(){
        Location testLocation = Location.CLARK;

        assertEquals(testLocation, Location.CLARK);

        assertEquals(testLocation.toString(), "Union County,07066");
    }

    @Test
    void exampleLocationTest2(){
        Location testLocation = Location.BRIDGEWATER;
        assertEquals(testLocation, Location.BRIDGEWATER);
        assertEquals(testLocation.toString(), "Somerset County,08807");
    }

    @Test
    void exampleLocationTest3(){
        Location testLocation = Location.EDISON;
        assertEquals(testLocation, Location.EDISON);
        assertEquals(testLocation.toString(), "Middlesex County,08817");
    }
}