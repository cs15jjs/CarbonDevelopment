package carbon.zeroevents.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import carbon.zeroevents.R;
import carbon.zeroevents.SettingsPage.AboutActivity;
import carbon.zeroevents.SettingsPage.Accessibility;
import carbon.zeroevents.SettingsPage.AccountActivity;
import carbon.zeroevents.SettingsPage.HelpActivity;
import carbon.zeroevents.SettingsPage.LocationActivity;
import carbon.zeroevents.SettingsPage.NotificationActivity;

/**
 * Created by alysha on 03/04/2018.
 */

public class SettingsFragment extends Fragment {

    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.settings_tab,container,false);

        activity = this.getActivity();

        String[] settingOptions = new String[] {"Account","Location", "Notifications","Accessibility", "Help", "About" };
        ListView listView = (ListView) view.findViewById(R.id.settingMenu) ;

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String> (activity,android.R.layout.simple_list_item_1,
                settingOptions);

        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0)
                {
                    Intent intent = new Intent(view.getContext(), AccountActivity.class);
                    startActivityForResult(intent,0);
                }

                if(position == 1)
                {
                    Intent intent = new Intent(view.getContext(), LocationActivity.class);
                    startActivityForResult(intent,1);
                }
                if(position == 2)
                {
                    Intent intent = new Intent(view.getContext(),NotificationActivity.class);
                    startActivityForResult(intent,2);
                }
                if(position == 3)
                {
                    Intent intent = new Intent(view.getContext(), Accessibility.class);
                    startActivityForResult(intent,3);
                }
                if(position == 4)
                {
                    Intent intent = new Intent(view.getContext(), HelpActivity.class);
                    startActivityForResult(intent,4);
                }
                if(position == 5)
                {
                    Intent intent = new Intent(view.getContext(), AboutActivity.class);
                    startActivityForResult(intent,5);
                }
            }
        });




        return view;
    }
}
