package carbon.zeroevents.Manual_User;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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

import carbon.zeroevents.MainActivity;
import carbon.zeroevents.R;

/**
 * Created by Owner on 16/04/2018.
 */

public class SignUp extends AppCompatActivity {
    private static final String TAG = SignUp.class.getSimpleName();
    private EditText username, email, password, first_name, last_init;
    private Button createAccButton;
    private ProgressDialog progressDialog;
    private UserSession session;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        email = (EditText) findViewById(R.id.emailInput);
        password = (EditText) findViewById(R.id.passwordInput);
        username = (EditText) findViewById(R.id.usernameInput);
        first_name = (EditText) findViewById(R.id.firstNameInput);
        last_init = (EditText) findViewById(R.id.lastInitInput);
        createAccButton = (Button) findViewById(R.id.createAccountButton);
        progressDialog = new ProgressDialog(this);
        session = new UserSession(this);
        userInfo = new UserInfo(this);

        if (session.isUserLoggedin()) {
            startActivity(new Intent(this, MainActivity.class));
//            intent.putExtra("position", 2);
            finish();
        }

        createAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uName = username.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String first = first_name.getText().toString().trim();
                String last = last_init.getText().toString().trim();

                signUp(uName, mail, pass, first, last);
            }
        });
    }

    private void signUp(final String uName, final String mail, final String pass, final String first, final String last) {
        //Tag to cancel the request
        String tag_string_req = "req_signup";
        progressDialog.setMessage("Signing you Up ..");
        progressDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, Utils.REGISTER_URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                progressDialog.dismiss();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    //Check for error node in json
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

                        Intent intent = new Intent(SignUp.this, MainActivity.class);
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
                Log.e(TAG, "Create account error: " + error.getMessage());
                toast("Response Error Listener error occured");
                progressDialog.hide();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //Posting parameters to login URL
                Map<String, String> params = new HashMap<>();
                params.put("email", mail);
                params.put("username", uName);
                params.put("password", pass);
                params.put("first_name", first);
                params.put("last_init", last);
                return params;
            }
        };
        //Adding request to the request queue
        AndroidLoginController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    private void toast(String x) {Toast.makeText(this, x, Toast.LENGTH_SHORT).show();}

}
