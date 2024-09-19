import java.util.Calender;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;


    //constructor to make the Date obj
    public Date (int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    //check if year is leap year
    private boolean isLeapYear() {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                if (year % QUATERCENTENNIAL == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    //check if the date is a valid calendar date
    public boolean isValid() {

    }
}