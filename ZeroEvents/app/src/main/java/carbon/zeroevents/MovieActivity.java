package carbon.zeroevents;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import carbon.zeroevents.Fragments.ExploreFragment;

/**
 * Created by Owner on 29/01/2018.
 */

public class MovieActivity extends AppCompatActivity {

    private String TAG = MovieActivity.class.getSimpleName();
    Intent intent = getIntent();
    String str = ExploreFragment.MOVIE_ID;
    TextView overviewTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.movie_activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.save_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        overviewTextView = (TextView)findViewById(R.id.toolbar_title);
        Toast.makeText(MovieActivity.this, "movie_id" + str, Toast.LENGTH_LONG).show();
        new WebPull().execute();

    }

    private class WebPull extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(MovieActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            JSONParser sh = new JSONParser();
            // Making a request to url and getting response
            String url = "https://api.themoviedb.org/3/movie/" + str + "?api_key=3df5f7f0fa2c63de5a5f0687a402d65f&language=en-GB";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting movie information movie
                    String backdrop_path = jsonObj.getString("backdrop_path");
                    String poster_path = jsonObj.getString("poster_path");
                    String id = jsonObj.getString("id");
                    String movie_title = jsonObj.getString("title");
                    String movie_release = jsonObj.getString("release_date");
                    String movie_overview = jsonObj.getString("overview");
                    String video = jsonObj.getString("video");

                    overviewTextView.setText(movie_title);

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }
    }
}
