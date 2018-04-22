package carbon.zeroevents.Fragments;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import carbon.zeroevents.Fragments.SocialMediaSignUp;
import carbon.zeroevents.Manual_User.SignUp;
import carbon.zeroevents.Manual_User.UserSession;
import carbon.zeroevents.R;

/**
 * Created by staceypowell on 21/04/2018.
 */

public class SignUpOptions extends AppCompatActivity implements View.OnClickListener{

    private Button manualSignUpBut;
    private Button facebookSignUpBut;
    private Button googleSignUpBut;
    private UserSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_options);

        manualSignUpBut = (Button) findViewById(R.id.manualSignUpBut);
        facebookSignUpBut = (Button) findViewById(R.id.facebookSignUpBut);
        googleSignUpBut = (Button) findViewById(R.id.googleSignUpBut);

        manualSignUpBut.setOnClickListener(this);
        facebookSignUpBut.setOnClickListener(this);
        googleSignUpBut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.manualSignUpBut:
                startActivity(new Intent(this, SignUp.class));

                break;

            case R.id.facebookSignUpBut:
                startActivity(new Intent(this, SocialMediaSignUp.class));
                break;

            case R.id.googleSignUpBut:
                startActivity(new Intent( this, SocialMediaSignUp.class));
        }
    }
}
