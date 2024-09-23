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
}