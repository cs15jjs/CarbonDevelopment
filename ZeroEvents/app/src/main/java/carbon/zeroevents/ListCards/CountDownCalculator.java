package carbon.zeroevents.ListCards;


import android.os.CountDownTimer;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by mariopalmissolano on 12/03/2018.
 */

public class CountDownCalculator {
    public String countDown;
    private CountDownTimer countDownTimer;

    public CountDownCalculator(String datestr) {

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


        startTimer(total_millis);

    }

    //Start Countodwn method
    private void startTimer(Long milliseconds) {


        countDownTimer = new CountDownTimer(milliseconds, 1000) {



            public void onTick(long millisUntilFinished) {


                long day = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
                millisUntilFinished -= TimeUnit.DAYS.toMillis(day);

                long hour = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
                millisUntilFinished -= TimeUnit.HOURS.toMillis(hour);

                long minute = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
                millisUntilFinished -= TimeUnit.MINUTES.toMillis(minute);

                long second = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

                countDown = String.format("%02d:%02d:%02d:%02d", day, hour, minute, second);


                Log.d("countdown", countDown);

            }

            public void onFinish() {

                countDown = "In Cinemas";

            }
        }.start();

    }

    public String getCountDown() {
        return countDown;
    }


}
