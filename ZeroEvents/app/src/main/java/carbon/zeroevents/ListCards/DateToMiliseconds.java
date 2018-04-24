package carbon.zeroevents.ListCards;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mariopalmissolano on 12/03/2018.
 */

public class DateToMiliseconds {


    public Long DateToMiliseconds(String datestr){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar start_calendar = Calendar.getInstance();


        Date date = null;


        try {

            date = simpleDateFormat.parse(datestr);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        long end_millis = date.getTime();
        long start_millis = start_calendar.getTimeInMillis();
        long total_millis = (end_millis - start_millis);

        return total_millis;
    }

}
