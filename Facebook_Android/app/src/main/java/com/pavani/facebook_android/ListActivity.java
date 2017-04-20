package com.pavani.facebook_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private final String log_txt = "ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_list);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken==null || accessToken.isExpired()){
            // maybe login again
        }
        Log.i(log_txt, "In on Create");

//            Intent intent = getIntent();
//                String jsondata = intent.getStringExtra("jsondata");
//            JSONArray friendslist;
//                ArrayList<Friends> friends = new ArrayList<Friends>();
//
//        try {
//                friendslist = new JSONArray(jsondata);
//            for (int i=0; i < friendslist.length(); i++) {
//
//                    friends.add(new Friends(friendslist.getJSONObject(i).getString("name")));
//            }
//        } catch (JSONException e) {
//                e.printStackTrace();
//        }
        ArrayList<Friends> friendslist = new ArrayList<Friends>();
        friendslist.add(new Friends("Daniel"));
        friendslist.add(new Friends("Jackson"));
        friendslist.add(new Friends("Hanami"));
        friendslist.add(new Friends("Shon"));
        friendslist.add(new Friends("Ryan"));

        FriendsAdapter adapter = new FriendsAdapter(ListActivity.this, friendslist, R.color.friendsListColor);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                Intent intent = new Intent(ListActivity.this, WebActivity.class);

                                                startActivity(intent);
                                            }

        });


        }

    }
