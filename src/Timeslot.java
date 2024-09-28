/**
 * @author Varun
 */
public enum Timeslot {
    SLOT1 (9, 0),
    SLOT2 (10, 45),
    SLOT3 (11, 15),
    SLOT4 (13, 30),
    SLOT5 (15, 0),
    SLOT6 (16, 15);

    private final int hour;
    private final int minute;

    private Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public static Timeslot setTimeslot(String timeslot){
        return switch (timeslot){
            case "1" -> Timeslot.SLOT1;
            case "2" -> Timeslot.SLOT2;
            case "3" -> Timeslot.SLOT3;
            case "4" -> Timeslot.SLOT4;
            case "5" -> Timeslot.SLOT5;
            case "6" -> Timeslot.SLOT6;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return String.format("%d:%02d %s",
                getHour(), minute, getAMPM());
    }

    public String getAMPM(){
        if (hour >= 0 && hour < 12)
            return "AM";
        return "PM";
    }

    public int getHour() {
        if (hour > 12) {
            return hour - 12;
        }
        return hour;
    }



}

