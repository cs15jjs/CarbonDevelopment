package carbon.zeroevents.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import carbon.zeroevents.Manual_User.Login;
import carbon.zeroevents.Manual_User.UserInfo;
import carbon.zeroevents.Manual_User.UserSession;
import carbon.zeroevents.R;

/**
 * Created by Owner on 03/04/2018.
 */

public class ConnectFragment extends Fragment {

    private Button logoutButton, loginButton;
    private TextView tvEmail, tvUsername, tvFirst, tvLast, loggedinTV;
    private UserSession session;
    private UserInfo userInfo;
    private Activity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.connect_tab, container, false);

        activity = this.getActivity();

        session = new UserSession(activity);
        userInfo = new UserInfo(activity);

        logoutButton = (Button) view.findViewById(R.id.logOutButton);
        loginButton = (Button) view.findViewById(R.id.connectLoginButton);
        tvEmail = (TextView) view.findViewById(R.id.textViewEmail);
        tvUsername = (TextView) view.findViewById(R.id.textViewUsername);
        tvFirst = (TextView) view.findViewById(R.id.textViewFirstName);
        tvLast = (TextView) view.findViewById(R.id.textViewLast);
        loggedinTV = (TextView) view.findViewById(R.id.textViewStatus);

        if (!session.isUserLoggedin()) {
            loggedinTV.setText("YOU ARE NOT LOGGED IN ...");
            startActivity(new Intent(activity, Login.class));
        }else {
            loginButton.setVisibility(View.INVISIBLE);
        }

        setText();

        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                session.setLoggedin(false);
                userInfo.clearUserInfo();
                startActivity(new Intent(activity, Login.class));
                loggedinTV.setText("YOU ARE NOT LOGGED IN ...");
                loginButton.setVisibility(View.VISIBLE);
                setText();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity, Login.class));
            }
        });

        return view;
    }

    private void setText(){

        String username = userInfo.getKeyUsername();
        String email = userInfo.getKeyEmail();
        String firstN = userInfo.getKeyFirstName();
        String lastI = userInfo.getKeyLastInitial();

        tvUsername.setText(username);
        tvEmail.setText(email);
        tvFirst.setText(firstN);
        tvLast.setText(lastI);
    }
}
