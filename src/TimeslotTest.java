import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TimeslotTest {
    @Test
    void exampleTimeSlotTest(){
        Timeslot timeslotExample = Timeslot.SLOT1;

        assertEquals("9:00 AM", timeslotExample.toString());
        assertEquals(timeslotExample, Timeslot.SLOT1);
    }
}