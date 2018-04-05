package carbon.zeroevents;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import carbon.zeroevents.Fragments.ExploreFragment;

/**
 * Created by Owner on 29/01/2018.
 */

public class MovieActivity extends Activity {

    private String TAG = MovieActivity.class.getSimpleName();
    Intent intent = getIntent();
    String str = ExploreFragment.MOVIE_ID;
    TextView overviewTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.activity_movie);
        overviewTextView = (TextView)findViewById(R.id.full_movie_title);
        Toast.makeText(MovieActivity.this, "movie_id" + str, Toast.LENGTH_LONG).show();
        new WebPull().execute();

    }

    private class WebPull extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MovieActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();

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
