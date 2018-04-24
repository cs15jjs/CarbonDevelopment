package carbon.zeroevents.SettingsPage;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import carbon.zeroevents.NotificationPage.CustomAdapter;
import carbon.zeroevents.NotificationPage.NotificationChecklist;
import carbon.zeroevents.R;

public class NotificationActivity extends AppCompatActivity {

    int preSelectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ListView listView = (ListView) findViewById(R.id.notif_listview);


        final List<NotificationChecklist> checklists = new ArrayList<>();
        checklists.add(new NotificationChecklist(false, "Notifications Enabled"));
        checklists.add(new NotificationChecklist(false, "Friend Requests"));
        checklists.add(new NotificationChecklist(false, "Events"));


        final CustomAdapter adapter = new CustomAdapter(this, checklists);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                NotificationChecklist box = checklists.get(i);

                if (box.isSelected())
                    box.setSelected(false);

                else
                    box.setSelected(true);

                checklists.set(i, box);

                //now update adapter
                adapter.updateRecords(checklists);

            }
        });
    }

}