package carbon.zeroevents.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import carbon.zeroevents.JSONParser;
import carbon.zeroevents.MovieActivity;
import carbon.zeroevents.R;

/**
 * Created by Owner on 03/04/2018.
 */

public class ExploreFragment extends Fragment {

    public static final int CONNECTION_TIMEOUT = 10000;
    public static final int READ_TIMEOUT = 15000;
    private Activity activity;
    public static String MOVIE_ID = "";
    private String TAG = "EXPLORE_ACTIVITY";
    private ListView lv;
    ArrayList<HashMap<String, String>> movieList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.explore_tab,container,false);

        activity = this.getActivity();

        movieList = new ArrayList<>();

        lv = (ListView) view.findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("Hello ListView", "You clicked item: " + id + " at position: " + position);
                Intent myIntent = new Intent(activity, MovieActivity.class);
                MOVIE_ID = movieList.get((int) id).get("id");
                startActivity(myIntent);
            }
        });

        new ServerPull().execute();

        return view;
    }

    private class ServerPull extends AsyncTask<String, String, String> {

        ProgressDialog pdLoading = new ProgressDialog(activity);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String ... params) {

            try {

                // Enter URL address where your json file resides
                // Even you can make call to php file which returns json data
//                url = new URL("http://192.168.64.2/php_files/get_movie_info.php");
                url = new URL("http://orbiculate-captain.000webhostapp.com/Jo/get_movie_info.php");

            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return e.toString();
            }
            try {

                // Setup HttpURLConnection class to send and receive data from php and mysql
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("GET");

                // setDoOutput to true as we receive data from json file
                conn.setDoOutput(true);

            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                return e1.toString();
            }

            try {

                int response_code = conn.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {

                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    String newResult = result.toString();
                    newResult = newResult.substring(1, newResult.length() - 1);

                    // Pass data to onPostExecute method
                    return (newResult);

                } else {

                    return ("Unsuccessful");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {

            pdLoading.dismiss();

            pdLoading.dismiss();
            try {
                JSONArray movies = new JSONArray(result);

                // looping through All Contacts
                for (int i = 0; i < movies.length(); i++) {

                    JSONObject c = movies.getJSONObject(i);

                    String id = c.getString("movie_id");
                    String movie_title = c.getString("movie_title");
                    String movie_overview = c.getString("overview");

                    // tmp hash map for single movie
                    HashMap<String, String> movie = new HashMap<>();

                    // adding each child node to HashMap key => value
                    movie.put("movie_id", id);
                    movie.put("movie_title", movie_title);
                    movie.put("overview", movie_overview);

                    // adding movies to movies list
                    movieList.add(movie);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage() + result);
            }

            ListAdapter adapter = new SimpleAdapter(activity, movieList,
                    R.layout.list_item, new String[]{ "movie_title","overview"},
                    new int[]{R.id.movie_title, R.id.movie_overview});

            lv.setAdapter(adapter);
        }
    }
}