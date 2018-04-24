package carbon.zeroevents.Manual_User;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Owner on 16/04/2018.
 */

public class UserInfo {
    private static final String TAG = UserSession.class.getSimpleName();
    private static final String PREF_NAME = "userinfo";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_FIRST_NAME = "first_name";
    private static final String KEY_LAST_INITIAL = "last_init";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public UserInfo(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setUsername(String username){
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public void setEmail(String email){
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public void setFirstName(String first_name){
        editor.putString(KEY_FIRST_NAME, first_name);
        editor.apply();
    }

    public void setLastInitial(String last_init){
        editor.putString(KEY_LAST_INITIAL, last_init);
        editor.apply();
    }

    public void clearUserInfo(){
        editor.clear();
        editor.commit();
    }

    public String getKeyUsername(){return prefs.getString(KEY_USERNAME, "");}

    public String getKeyEmail(){return prefs.getString(KEY_EMAIL, "");}

    public String getKeyFirstName(){return prefs.getString(KEY_FIRST_NAME, "");}

    public String getKeyLastInitial(){return prefs.getString(KEY_LAST_INITIAL, "");}
}
