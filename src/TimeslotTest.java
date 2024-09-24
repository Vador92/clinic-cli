import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TimeslotTest {
    @Test
    void testValidTimeSlots() {
        Timeslot valid1 = Timeslot.fromSchedule("1");
        assertEquals(valid1, Timeslot.SLOT1);
        assertEquals(valid1.toString(), "9:00 AM");
        System.out.println(valid1.toString());

        Timeslot valid2 = Timeslot.fromSchedule("2");
        assertEquals(valid2, Timeslot.SLOT2);

        Timeslot valid3 = Timeslot.fromSchedule("3");
        assertEquals(valid3, Timeslot.SLOT3);

        Timeslot valid4 = Timeslot.fromSchedule("4");
        assertEquals(valid4, Timeslot.SLOT4);

        Timeslot valid5 = Timeslot.fromSchedule("5");
        assertEquals(valid5, Timeslot.SLOT5);

        Timeslot valid6 = Timeslot.fromSchedule("6");
        assertEquals(valid6, Timeslot.SLOT6);
        System.out.println(valid6.toString());
    }

    @Test
    void testInvalidTimeSlots() {
        Timeslot invalid1 = Timeslot.fromSchedule("A");
        if(invalid1==null){
            System.out.println("A is not a valid time slot.");
        }
        Timeslot invalid2 = Timeslot.fromSchedule("0");
        Timeslot invalid3 = Timeslot.fromSchedule("9");
    }

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

    @Test
    public void testFromScheduleSlot1() {
        Timeslot slot = Timeslot.fromSchedule("1");
        assertEquals(Timeslot.SLOT1, slot);
    }

    @Test
    public void testFromScheduleSlot2() {
        Timeslot slot = Timeslot.fromSchedule("2");
        assertEquals(Timeslot.SLOT2, slot);
    }

    @Test
    public void testFromScheduleSlot3() {
        Timeslot slot = Timeslot.fromSchedule("3");
        assertEquals(Timeslot.SLOT3, slot);
    }


}