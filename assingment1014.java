import java.util.Calendar;
import java.util.GregorianCalendar;


public class assingment1014{
    public static void main(String args[])
    {
         myDate date1 = new myDate();
         System.out.println("The date is... " + date1.getMonth() + "/" + date1.getDay() + "/" + date1.getYear());
         myDate date2 = new myDate(34355555133101L);
         System.out.println("The new date is " + date2.getMonth() + "/" + date2.getDay() + "/" + date2.getYear());
         System.out.println(date2);
    }
    
    }
    class myDate{
        GregorianCalendar date = new GregorianCalendar();
        //Create date with todays date
        public myDate()
        {
            date.set(Calendar.YEAR,2025);
            date.set(Calendar.MONTH,10);
            date.set(Calendar.DAY_OF_MONTH,6);
        }
        //Create date with default value, but add milliseconds from parameter value
        public myDate(long milli)
        {
            date.setTimeInMillis(milli);
        }
        //Create date with specific paramters
        public myDate(int m, int d, int y)
        {
            date.set(Calendar.YEAR, y);
            date.set(Calendar.MONTH, m);
            date.set(Calendar.DAY_OF_MONTH, d);
        }
        //Return year
        public int getYear()
        {
            return date.get(Calendar.YEAR);
        }
        //Return Month
        public int getMonth()
        {
            return date.get(Calendar.MONTH);
        }
        //Return Day
        public int getDay()
        {
            return date.get(Calendar.DAY_OF_MONTH);
        }
        //Add milliseconds to the current date
        public void setMillis(long millis)
        {
            date.setTimeInMillis(millis);
        }
    }
