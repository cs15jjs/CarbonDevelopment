package carbon.zeroevents.ListCards;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import carbon.zeroevents.ListCards.*;
import carbon.zeroevents.R;


/**
 * Created by mariopalmissolano on 10/03/2018.
 */

public class SetViewHolder extends RecyclerView.ViewHolder {

    public TextView TVmovieTitle, TVcountdown;

    public SetViewHolder(View itemView) {
        super(itemView);

        TVmovieTitle = (TextView) itemView.findViewById(R.id.movie_title_id);
        TVcountdown = (TextView) itemView.findViewById(R.id.movie_countdown_id);

    }

}
