package carbon.zeroevents.Manual_User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

import carbon.zeroevents.Fragments.ConnectFragment;
import carbon.zeroevents.MainActivity;
import carbon.zeroevents.R;

public class LoginOptions extends AppCompatActivity {

    private Button manualSignUpBut;
    LoginButton LoginButton;
    CallbackManager callbackManager;
    private static final String EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login_options);

        manualSignUpBut = (Button) findViewById(R.id.manualSignUpBut);
        manualSignUpBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manual();
            }
        });

        controls();
        loginWithFacebook();
    }


    public void manual(){
        Intent intent = new Intent (this, SignUp.class);
        startActivity(intent);
    }


    private void loginWithFacebook(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(LoginOptions.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    private void controls(){
        callbackManager = CallbackManager.Factory.create();
        LoginButton = (LoginButton)findViewById(R.id.facebookLoginBut);
        LoginButton.setReadPermissions(Arrays.asList(EMAIL));
    }



}
