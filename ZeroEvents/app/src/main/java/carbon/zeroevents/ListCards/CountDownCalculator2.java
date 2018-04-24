package carbon.zeroevents.ListCards;

<<<<<<< HEAD
import android.os.CountDownTimer;

=======
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;



import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
>>>>>>> 851002625edc4dd172df7d893d0aba6e3a70d2d2
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by mariopalmissolano on 12/03/2018.
 */

public class CountDownCalculator2 extends CountDownTimer {

    private GenericCallBack callback;
    private String countDown;
    private Date date = null;

    /**
     * @param callback
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
 *                          {@link #onTick(long)} callbacks.
     */
    public CountDownCalculator2(GenericCallBack callback, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);

        this.callback = callback;

    }

    @Override
    public void onTick(long millisUntilFinished) {



        long day = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
        millisUntilFinished -= TimeUnit.DAYS.toMillis(day);

        long hour = TimeUnit.MILLISECONDS.toHours(millisUntilFinished);
        millisUntilFinished -= TimeUnit.HOURS.toMillis(hour);

        long minute = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished);
        millisUntilFinished -= TimeUnit.MINUTES.toMillis(minute);

        long second = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);

        this.countDown = String.format("%02d:%02d:%02d:%02d", day, hour, minute, second);

        //Log.d("countdown", countDown);

        callback.execute(countDown);

    }

    @Override
    public void onFinish() {

        callback.execute("In cinemas");

    }

}
