package carbon.zeroevents.SettingsPage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import carbon.zeroevents.HelpPage.HelpList;
import carbon.zeroevents.HelpPage.MyAdapter;
import carbon.zeroevents.R;

public class HelpActivity extends AppCompatActivity {

private RecyclerView recyclerView;
private RecyclerView.Adapter adapter;

private List<HelpList> listitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listitems = new ArrayList<>();

        for(int i = 0 ; i <= 10 ; i++)
        { HelpList listitem = new HelpList(
            "Topic" + i,
            "Help around the topic is found in here"
            );

        listitems.add(listitem);

        }

        adapter = new MyAdapter(listitems,this);
        recyclerView.setAdapter(adapter);
    }


}
