package com.pavani.facebook_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {
    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private final String log_txt="LOGMainActivity";
    private JSONArray jsonArray;
    Profile profile;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);
        info = (TextView) findViewById(R.id.info);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        intent = new Intent(MainActivity.this, ListActivity.class);
        profile = Profile.getCurrentProfile();
        if(profile!=null){
                startActivity(intent);
            }
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {

//                    new GraphRequest(
//                            AccessToken.getCurrentAccessToken(),
//                            "/me/invitable_friends",               // tried taggable_friends,users_friends
//                            null,
//                            HttpMethod.GET,
//                            new GraphRequest.Callback() {
//                                public void onCompleted(GraphResponse response) {
//                                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
//                                    try {
//                                        jsonArray = response.getJSONObject().getJSONArray("data");
//                                        intent.putExtra("jsondata", jsonArray.toString());
//                                        startActivity(intent);
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                    ).executeAsync();

                    startActivity(intent);
                }

                @Override
                public void onCancel() {
                    info.setText("User Cancelled Sign in ");
                }

                @Override
                public void onError(FacebookException e) {
                    info.setText("Some Error Occured. Please try again");
                }
            });

        }

    public void onPause(){
        super.onPause();
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(log_txt, " In onActivityResult");
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
