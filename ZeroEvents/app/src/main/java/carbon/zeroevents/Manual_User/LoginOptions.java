package carbon.zeroevents.Manual_User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import carbon.zeroevents.R;

public class LoginOptions extends AppCompatActivity {

    private Button manualSignUpBut, facebookLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_options);

        manualSignUpBut = (Button) findViewById(R.id.manualSignUpBut);
        facebookLogin = (Button) findViewById(R.id.facebookLoginBut);

        manualSignUpBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manual();
            }
        });



    }

    public void manual(){
        Intent intent = new Intent (this, SignUp.class);
        startActivity(intent);
    }


}
