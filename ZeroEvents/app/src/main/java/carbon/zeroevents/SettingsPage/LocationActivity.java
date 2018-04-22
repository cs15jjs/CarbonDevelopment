package carbon.zeroevents.SettingsPage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import carbon.zeroevents.LocationPage.LocationChecklist;
import carbon.zeroevents.LocationPage.Location_customAdapter;
import carbon.zeroevents.R;

public class LocationActivity extends AppCompatActivity {

    int preSelectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ListView listView = (ListView) findViewById(R.id.location_list);


        final List<LocationChecklist> checklists = new ArrayList<>();
        checklists.add(new LocationChecklist(false, "Location Enabled"));
        checklists.add(new LocationChecklist(false, "Friends can view locations"));

        final Location_customAdapter adapter = new Location_customAdapter(this, checklists);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                LocationChecklist box = checklists.get(i);

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
