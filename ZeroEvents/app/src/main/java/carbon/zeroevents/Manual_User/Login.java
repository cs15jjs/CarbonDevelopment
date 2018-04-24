package carbon.zeroevents.Manual_User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import carbon.zeroevents.Fragments.ConnectFragment;
import carbon.zeroevents.MainActivity;
import carbon.zeroevents.R;

/**
 * Created by Owner on 16/04/2018.
 */

public class Login extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = Login.class.getSimpleName();
    private EditText email, password;
    private Button login, signUp;
    private ProgressDialog progressDialog;
    private UserSession session;
    private UserInfo userInfo;
    private ViewPager mPagerAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        email = (EditText) findViewById(R.id.emailInput);
        password = (EditText) findViewById(R.id.passwordInput);
        login = (Button) findViewById(R.id.loginButton);
        signUp = (Button) findViewById(R.id.openSignUpButton);
        progressDialog = new ProgressDialog(this);
        session = new UserSession(this);
        userInfo = new UserInfo(this);

        if (session.isUserLoggedin()) {
            startActivity(new Intent(this, MainActivity.class));
//          intent.putExtra("position", 2);
            finish();
        }

        login.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                String uName = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                login(uName, pass);
                break;

            case R.id.openSignUpButton:
                startActivity(new Intent(this, SignUp.class));
                break;
        }
    }

    private void login(final String email, final String password) {
        //tag used to cancel the request
        String tag_string_reg = "req_login";
        progressDialog.setMessage("Logging in ...");
        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, Utils.LOGIN_URL, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                progressDialog.dismiss();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    //check for error node in json
                    if (!error) {
                        //now store the user in SQLite
                        JSONObject user = jObj.getJSONObject("user");
                        String uName = user.getString("username");
                        String email = user.getString("email");
                        String f_name = user.getString("first_name");
                        String l_init = user.getString("last_init");

                        //inserting row in users tables
                        userInfo.setEmail(email);
                        userInfo.setUsername(uName);
                        userInfo.setFirstName(f_name);
                        userInfo.setLastInitial(l_init);
                        session.setLoggedin(true);

                        Intent intent = new Intent(Login.this, MainActivity.class);
//                        intent.putExtra("position", 2);
                        startActivity(intent);
                        finish();
                    } else {
                        //error in login, get error message
                        String errorMsg = jObj.getString("error_msg");
                        toast(errorMsg);
                    }
                } catch (JSONException e) {
                    //JSON error
                    e.printStackTrace();
                    toast("JSON error: " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login error: " + error.getMessage());
                toast("Response Error Listener error occured");
                progressDialog.hide();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //Posting parameters to login URL
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                //params.put("username", username);

                return params;
            }
        };

        //Adding request to the request queue
        AndroidLoginController.getInstance().addToRequestQueue(strReq, tag_string_reg);
    }

    private void toast(String x) {Toast.makeText(this, x, Toast.LENGTH_SHORT).show();}





}
