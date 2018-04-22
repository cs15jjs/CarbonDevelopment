package carbon.zeroevents.LocationPage;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import carbon.zeroevents.NotificationPage.NotificationChecklist;
import carbon.zeroevents.R;


public class Location_customAdapter extends BaseAdapter {

    Activity activity;
    List<LocationChecklist> checklists;
    LayoutInflater inflater;




    public Location_customAdapter(Activity activity) {
        this.activity = activity;
    }

    public Location_customAdapter(Activity activity, List<LocationChecklist> checklists) {
        this.activity   = activity;
        this.checklists = checklists;

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

        carbon.zeroevents.LocationPage.Location_customAdapter.ViewHolder holder = null;

        if (view == null){

            view = inflater.inflate(R.layout.location_list, viewGroup, false);

            holder = new carbon.zeroevents.LocationPage.Location_customAdapter.ViewHolder();

            holder.option = (TextView)view.findViewById(R.id.option);
            holder.ivCheckBox = (ImageView) view.findViewById(R.id.ivCheckBox);

            view.setTag(holder);
        }else
            holder = (carbon.zeroevents.LocationPage.Location_customAdapter.ViewHolder)view.getTag();

        LocationChecklist box = checklists.get(i);

        holder.option.setText(box.getOption());

        if (box.isSelected())
            holder.ivCheckBox.setBackgroundResource(R.drawable.checked);

        else
            holder.ivCheckBox.setBackgroundResource(R.drawable.check);

        return view;

    }

    public void updateRecords(List<LocationChecklist> checklists){
        this.checklists = checklists;

        notifyDataSetChanged();
    }

    class ViewHolder{

        TextView option;
        ImageView ivCheckBox;

    }
}



