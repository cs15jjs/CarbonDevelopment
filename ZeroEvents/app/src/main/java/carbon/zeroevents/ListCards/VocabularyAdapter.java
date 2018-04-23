package carbon.zeroevents.ListCards;


import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.Calendar;
import java.util.Collections;

import java.util.List;

import testerson.testy.fragment_and_tabtest.ListCards.*;
import testerson.testy.fragment_and_tabtest.R;

/**
 * Created by mariopalmissolano on 10/03/2018.
 */

public class VocabularyAdapter extends RecyclerView.Adapter<SetViewHolder> {
    private Calendar start_calendar = Calendar.getInstance();
    CountDownCalculator2 countDownCalculator;
    long start_millis;
    long end_millis;
    long total_millis;

    private Activity activity;
    List<Item> items = Collections.emptyList();

    private OnTapListener onTapListener;

    public VocabularyAdapter(Activity activity, List<Item> items) {

        this.activity = activity;
        this.items = items;

    }

    @Override
    public SetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_movie, parent, false);

        return new SetViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final SetViewHolder holder, final int position) {


        holder.TVmovieTitle.setText(items.get(position).getMovieTitle());
        // holder.TVcountdown.setText(items.get(position).getReleaseDateMillis());


        end_millis = Long.parseLong(items.get(position).getReleaseDateMillis());
        start_millis = start_calendar.getTimeInMillis();


        CountDownTimer countDownTimer = new CountDownCalculator2(new GenericCallBack() {
            @Override
            public Object execute(Object value) {
                holder.TVcountdown.setText((String) value);
                return null;
            }
        }, end_millis - start_millis, 1000).start();


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onTapListener != null) {

                    onTapListener.OnTapView(position);

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnTapListener(OnTapListener onTapListener) {

        this.onTapListener = onTapListener;

    }

}