package carbon.zeroevents.Manual_User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import carbon.zeroevents.R;

public class LoginOptions extends AppCompatActivity {

    private Button manualSignUpBut, facebookSignUpBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_options);

        manualSignUpBut = (Button) findViewById(R.id.manualSignUpBut);
        facebookSignUpBut = (Button) findViewById(R.id.facebookSignUpBut);

        manualSignUpBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manual();
            }
        });

        facebookSignUpBut.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manual();
            }
        }));
    }

    public void manual(){
        Intent intent = new Intent (this, SignUp.class);
        startActivity(intent);
    }
}
