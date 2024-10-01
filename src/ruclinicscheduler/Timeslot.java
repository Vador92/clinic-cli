package ruclinicscheduler;

/**
 * This is an enum class for the 6 specific timeslots
 * @author Varun Doreswamy
 */
public enum Timeslot {
    SLOT1 (9, 0),
    SLOT2 (10, 45),
    SLOT3 (11, 15),
    SLOT4 (13, 30),
    SLOT5 (15, 0),
    SLOT6 (16, 15);

    // instance variables
    private final int hour;
    private final int minute;

    /**
     * This method creates a new enum object based on hour and minute
     * @param hour is the hour needed for the timeslot
     * @param minute is the minute need for the timeslot
     */
    Timeslot(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * This gets the timeslot in a number form, for a specific error.
     * @return String of the timeslot
     */
    public String getTime(){
        return switch (this){
            case SLOT1 -> "1";
            case SLOT2 -> "2";
            case SLOT3 -> "3";
            case SLOT4 -> "4";
            case SLOT5 -> "5";
            case SLOT6 -> "6";
            default -> "";
        };
    }

    /**
     * This creates a new timeslot object if a valid time.
     * @param timeslot is used to see if timeslot is valid
     * @return Timeslot that corresponds to the 6 timeslots, or null
     */
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

    /**
     * This returns a formatted String of the timeslot
     * @return String of formatted time, with AM/PM
     */
    @Override
    public String toString() {
        return String.format("%d:%02d %s",
                getHour(), minute, getAMPM());
    }

    /**
     * This gets the time of day based on AM or PM
     * @return String of the time of day
     */
    public String getAMPM(){
        if (hour >= 0 && hour < 12)
            return "AM";
        return "PM";
    }

    /**
     * This gets the hour of day modified to standard time
     * @return integer of the adjusted time from military time
     */
    public int getHour() {
        if (hour > 12) {
            return hour - 12;
        }
        return hour;
    }
}

