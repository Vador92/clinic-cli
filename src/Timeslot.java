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

