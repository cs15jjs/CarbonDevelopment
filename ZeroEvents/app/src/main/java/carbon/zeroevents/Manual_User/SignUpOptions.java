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
 * Created by Stacey on 16/04/2018.
 */

public class SignUpOptions extends AppCompatActivity {
    //private static final String TAG = SignUp.class.getSimpleName();
    private Button manualSignUpBut;
    private Button facebookSignUpBut;
    private Button googleSignUpBut;
    private ProgressDialog progressDialog;
    private UserSession session;
    private UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_options);

        manualSignUpBut = (Button) findViewById(R.id.manualSignUpBut);
        facebookSignUpBut = (Button) findViewById(R.id.facebookSignUpBut);
        googleSignUpBut = (Button) findViewById(R.id.googleSignUpBut);
        progressDialog = new ProgressDialog(this);
        session = new UserSession(this);
        userInfo = new UserInfo(this);


        if (session.isUserLoggedin()) {
            startActivity(new Intent(this, MainActivity.class));
//            intent.putExtra("position", 2);
            finish();
        }

        manualSignUpBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(this, MainActivity.class));
//            intent.putExtra("position", 2);
                finish();
            }
        });
    }
}
