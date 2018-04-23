package carbon.zeroevents.HelpPage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import carbon.zeroevents.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<HelpList> listitems;
    private Context context;

    public MyAdapter(List<HelpList> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.help_list, parent, false);

        return new ViewHolder(v);
    }



    @Override

    public void onBindViewHolder(ViewHolder holder, int position) {
        HelpList listitem = listitems.get(position);

        holder.topic.setText(listitem.getTopic());
        holder.answer.setText(listitem.getAnswer());

    }

    @Override
    public int getItemCount() {
        return listitems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView topic;
        public TextView answer;

        public ViewHolder(View itemView) {
            super(itemView);

            topic= (TextView) itemView.findViewById(R.id.topic) ;
            answer= (TextView) itemView.findViewById(R.id.answer) ;
        }
    }
}