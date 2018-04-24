package carbon.zeroevents.SettingsPage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import carbon.zeroevents.HelpPage.HelpList;
import carbon.zeroevents.HelpPage.MyAdapter;
import carbon.zeroevents.R;

public class HelpActivity extends AppCompatActivity {

    private static final String URL_DATA = "http://orbiculate-captain.000webhostapp.com/Alysha/FAQ.php";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<HelpList> listitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listitems = new ArrayList<>();

        loadRecyclerViewData();


    }
    private void loadRecyclerViewData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try{

                        JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("FAQ");

                            for(int i = 0; i < array.length();i++){
                                JSONObject o = array.getJSONObject(i);
                                HelpList item = new HelpList(
                                        o.getString("Topics"),
                                        o.getString("Answer")
                                );

                                listitems.add(item);
                            }

                            adapter = new MyAdapter(listitems,getApplicationContext());
                            recyclerView.setAdapter(adapter);

                    }catch(JSONException e)

                    {
                        e.printStackTrace();
                    }
                }
            },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError VolleyError) {
                        Toast.makeText(getApplicationContext(),VolleyError.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        }





    }
