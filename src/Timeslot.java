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

    @Override
    public String toString() {
        return hour + ":" + getMinute() + " " + getAMPM();
    }

    // this function gets if its first half of day or second half
    /*
    If time is greater than 12, and less than 0, then PM
    else AM
     */
    public String getAMPM(){
        if (hour >= 0 && hour < 12){
            return "AM";
        }
        else{
            return "PM";
        }
    }

    public String getMinute(){
        if (minute == 0){
            return "00";
        }
        else{
            return "" + minute;
        }
    }
}

