package carbon.zeroevents.Manual_User;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
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
