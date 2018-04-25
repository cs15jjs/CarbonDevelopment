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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import carbon.zeroevents.Manual_User.Login;
import carbon.zeroevents.Manual_User.UserInfo;
import carbon.zeroevents.Manual_User.UserSession;



public class MovieActivity extends AppCompatActivity {

    private String TAG = MovieActivity.class.getSimpleName();
    String str = MainActivity.MOVIE_ID;
    TextView toolbar_title, overviewTV, releaseTV, popularityTV, sorrynoVidTV;
    ImageView movieIV, ratingIV;
    JSONObject jsonObj;
    String youtubeKey, movie_release, movie_title;
    String URL = "http://orbiculate-captain.000webhostapp.com/Jo/get_movie_trailer.php";
    long today_timeInMillis, end_timeInMillis;
    FloatingActionButton fab;
    private UserInfo userInfo;
    private UserSession userSession;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from new_activity.xml
        setContentView(R.layout.movie_activity_details);

        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        toolbar_title = findViewById(R.id.toolbar_title);
        overviewTV = findViewById(R.id.overviewTV);
        releaseTV = findViewById(R.id.releaseTV);
        popularityTV = findViewById(R.id.popularityTV);
        movieIV = findViewById(R.id.movieIV);
        ratingIV = findViewById(R.id.rating);
        userInfo = new UserInfo(this);
        userSession = new UserSession(this);

        fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.save_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userSession.isUserLoggedin()){
                    new AddMoviePush().execute();
                }else {
                    startActivity(new Intent(MovieActivity.this, Login.class));
                }
            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Toast.makeText(MovieActivity.this, "movie_id" + str, Toast.LENGTH_LONG).show();
        new WebPull().execute();

        youtubeServerPull();

    }

    private class AddMoviePush extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String end_date = movie_release;
            Calendar todaysDate = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date final_end_date = sdf.parse(end_date);
                today_timeInMillis = todaysDate.getTimeInMillis();
                end_timeInMillis = final_end_date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String user_id = userInfo.getKeyUserID();
            String new_URL = "http://orbiculate-captain.000webhostapp.com/Mario/addMovie.php/?userID=" + user_id + "&movieID=" + str;
            new_URL += "&movieTitle=" + movie_title.replace(" ", "%20") + "&releaseDate=" + end_timeInMillis + "&dateAdded=" + today_timeInMillis;

            Log.e(TAG, "Url: " + new_URL);

            JSONParser sh = new JSONParser();
            String jsonStr = sh.makeServiceCall(new_URL);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                fab.setImageResource(R.drawable.saved_button);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(MovieActivity.this, "Your movie has been saved." , Toast.LENGTH_LONG).show();

        }
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

                    jsonObj = new JSONObject(jsonStr);

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

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                // Getting movie information movie
                String backdrop_path = jsonObj.getString("backdrop_path");
                String poster_path = jsonObj.getString("poster_path");
                movie_title = jsonObj.getString("title");
                movie_release = jsonObj.getString("release_date");
                String movie_overview = jsonObj.getString("overview");
                int popularity = jsonObj.getInt("popularity");

                toolbar_title.setText(movie_title);
                overviewTV.setText(movie_overview);
                releaseTV.setText(getString(R.string.release_date, movie_release));

                if (poster_path != null) {
                    String imageURL = "https://image.tmdb.org/t/p/w780" + poster_path;
                    Picasso.get().load(imageURL).into(movieIV);
                } else if (backdrop_path != null) {
                    String b_imageURL = "https://image.tmdb.org/t/p/w780" + backdrop_path;
                    Picasso.get().load(b_imageURL).into(movieIV);
                }

                if (popularity <= 1) {
                    popularityTV.setText(R.string.not_hot);
                    ratingIV.setImageResource(R.drawable.not_hot_bad);
                } else if (popularity > 1 && popularity < 3) {
                    popularityTV.setText(R.string.warm);
                    ratingIV.setImageResource(R.drawable.warm_okay);
                } else if (popularity >= 3 && popularity < 8) {
                    popularityTV.setText(R.string.hot);
                    ratingIV.setImageResource(R.drawable.hot_great);
                } else if (popularity >= 8) {
                    popularityTV.setText(R.string.hot_hot_hot);
                    ratingIV.setImageResource(R.drawable.fire_very_hot);
                }
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
        }
    }

    private void youtubeServerPull() {

        final StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "Response from url: " + response);

                        if (response.length() > 2) {
                            youtubeKey = response;
                            initWebView();
                        } else {
                            sorrynoVidTV = findViewById(R.id.sorryNoVidTV);
                            sorrynoVidTV.setVisibility(View.VISIBLE);
                            sorrynoVidTV.setText(R.string.oop_sorry);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MovieActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("movie_id", str);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        requestQueue.cancelAll(this);
    }

    private void initWebView() {
        WebView webview = findViewById(R.id.trailer_webview);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        WebSettings ws = webview.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setLoadWithOverviewMode(true);
        ws.setUseWideViewPort(true);


        String html = "<iframe width=\"100%\" height=\"450\" src=\"https://www.youtube.com/embed/" + youtubeKey + "\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>";

        Log.e(TAG, "youtube web address: " + html);

        webview.loadData(html, "text/html", "utf-8");
    }
}
