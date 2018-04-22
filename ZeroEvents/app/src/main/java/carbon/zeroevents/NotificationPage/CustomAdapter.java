package carbon.zeroevents.NotificationPage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import carbon.zeroevents.R;


public class CustomAdapter extends BaseAdapter {

    Activity activity;
    List<NotificationChecklist> checklists;
    LayoutInflater inflater;




    public CustomAdapter(Activity activity) {
        this.activity = activity;
    }

    public CustomAdapter(Activity activity, List<NotificationChecklist> users) {
        this.activity   = activity;
        this.checklists = users;

        inflater = activity.getLayoutInflater();
    }


    @Override
    public int getCount() {
        return checklists.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (view == null){

            view = inflater.inflate(R.layout.notif_list, viewGroup, false);

            holder = new ViewHolder();

            holder.n_option = (TextView)view.findViewById(R.id.n_option);
            holder.ivCheckBox = (ImageView) view.findViewById(R.id.ivCheckBox);

            view.setTag(holder);
        }else
            holder = (ViewHolder)view.getTag();

        NotificationChecklist box = checklists.get(i);

        holder.n_option.setText(box.getOption());

        if (box.isSelected())
            holder.ivCheckBox.setBackgroundResource(R.drawable.checked);

        else
            holder.ivCheckBox.setBackgroundResource(R.drawable.check);

        return view;

    }

    public void updateRecords(List<NotificationChecklist> checklists){
        this.checklists = checklists;

        notifyDataSetChanged();
    }

    class ViewHolder{

        TextView n_option;
        ImageView ivCheckBox;

    }
}

