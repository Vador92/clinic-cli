import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TimeslotTest {


    @Test
    public void testTimeslotSlot1() {
        Timeslot slot = Timeslot.SLOT1;
        assertEquals("9:00 AM", slot.toString());
    }

    @Test
    public void testTimeslotSlot2() {
        Timeslot slot = Timeslot.SLOT2;
        assertEquals("10:45 AM", slot.toString());
    }

    @Test
    public void testTimeslotSlot3() {
        Timeslot slot = Timeslot.SLOT3;
        assertEquals("11:15 AM", slot.toString());
    }

    @Test
    public void testTimeslotSlot4() {
        Timeslot slot = Timeslot.SLOT4;
        assertEquals("1:30 PM", slot.toString());
    }

    @Test
    public void testTimeslotSlot5() {
        Timeslot slot = Timeslot.SLOT5;
        assertEquals("3:00 PM", slot.toString());
    }

    @Test
    public void testTimeslotSlot6() {
        Timeslot slot = Timeslot.SLOT6;
        assertEquals("4:15 PM", slot.toString());
    }




}