package com.kpark.mobilite.touiteur;

import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.twitter.sdk.android.tweetui.UserTimeline;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogedActivity extends ListActivity {

    @BindView(R.id.title) TextView title;

    @BindView(R.id.searchtweet) SearchView search;

    private static final String TITLE_START = "Tweet of ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_tweet_activity);
        ButterKnife.bind(this);
        String userName = getIntent().getStringExtra("userName");
        title.setText(TITLE_START + userName);
        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName(userName)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getTweets();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    public void getTweets() {
        finish();
        startActivity(getIntent().putExtra("userName", search.getQuery().toString()));
    }
}