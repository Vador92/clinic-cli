import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public boolean isValid() { //check if the date is a valid calendar date

        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    System.out.println("");
                }
            }
        }
        return true;
    }

    @Override
    public int compareTo(Date o) {
        return 0;
    }

    @Override
    public String toString(){
        return "";
    }
}
