package carbon.zeroevents.Manual_User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import carbon.zeroevents.MainActivity;
import carbon.zeroevents.R;

public class LoginOptions extends AppCompatActivity {

    private Button manualSignUpBut;
    LoginButton loginButton;
    CallbackManager callbackManager;
    //private static final String EMAIL = "email";
    private UserSession session;
    AccessToken accessToken = AccessToken.getCurrentAccessToken();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login_options);

        session = new UserSession(this);

        manualSignUpBut = (Button) findViewById(R.id.manualSignUpBut);
        manualSignUpBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manual();
            }
        });

        loginButton = (LoginButton) findViewById(R.id.facebookLoginBut);
        loginButton.setReadPermissions("email", "public_profile");

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String userid = loginResult.getAccessToken().getUserId();

                GraphRequest graphrequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        displayUserInfo(object);
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "first_name, last_name, email, id");
                graphrequest.setParameters(parameters);
                graphrequest.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    public void manual(){
        Intent intent = new Intent (this, SignUp.class);
        startActivity(intent);
    }

    public void displayUserInfo(JSONObject object){
        String first_name, last_name, email, id;
        first_name = " ";
        last_name = " ";
        email = " ";
        id = " ";
        try {
            first_name = object.getString("first_name");
            last_name = object.getString("last_name");
            email = object.getString("email");
            id = object.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        TextView tv_name, tv_email, tv_id;
        tv_name=(TextView)findViewById(R.id.TV_name);
        tv_email=(TextView)findViewById(R.id.TV_email);
        tv_id=(TextView)findViewById((R.id.TV_id));

        tv_name.setText(first_name + " " + last_name);
        tv_email.setText(email);
        tv_id.setText(id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
//session.setLoggedin(true);
//
//        Intent intent = new Intent(LoginOptions.this, MainActivity.class);
//        startActivity(intent);