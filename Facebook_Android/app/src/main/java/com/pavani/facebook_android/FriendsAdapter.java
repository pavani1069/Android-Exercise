package com.pavani.facebook_android;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pavani on 4/19/2017.
 */

public class FriendsAdapter extends ArrayAdapter<Friends> {

    private static final String LOG_TAG = FriendsAdapter.class.getSimpleName();
    int myColor=0;

    public FriendsAdapter(Context context, ArrayList<Friends> friendsArrayList, int color) {
        super(context,0, friendsArrayList);
    }

    @NonNull

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.content_list, parent, false);
        }

        Friends currentFriend = getItem(position);

        TextView friendName = (TextView) listItemView.findViewById(R.id.friendName);
        friendName.setText(currentFriend.getFriendName());
        return  listItemView;

    }
}
