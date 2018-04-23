package carbon.zeroevents;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import carbon.zeroevents.Fragments.ConnectFragment;
import carbon.zeroevents.Fragments.ExploreFragment;
import carbon.zeroevents.Fragments.HomeFragment;
import carbon.zeroevents.Fragments.SectionPageAdapter;
import carbon.zeroevents.Fragments.SettingsFragment;
import carbon.zeroevents.R;

public class SettingsActivity extends AppCompatActivity {
    ImageButton helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_tab);

        helpButton = (ImageButton) findViewById(R.id.helpIcon);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent helpIntent = new Intent(SettingsActivity.this, HelpActivity.class);
                startActivity(helpIntent);
            }
        });
    }
}
