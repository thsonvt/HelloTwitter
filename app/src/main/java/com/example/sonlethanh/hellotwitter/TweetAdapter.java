package com.example.sonlethanh.hellotwitter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sonlethanh.hellotwitter.model.Tweet;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by sonlethanh on 29/6/14.
 */
public class TweetAdapter extends ArrayAdapter<Tweet>{

    private LayoutInflater inflater;
    private List<Tweet> tweets;

//    public TweetAdapter(Activity activity, String[] items){
//        super(activity, R.layout.row_tweet, items);
//        inflater = activity.getWindow().getLayoutInflater();
//    }

    public TweetAdapter(Activity activity, List<Tweet> items){
        super(activity, R.layout.row_tweet, items);
        this.tweets = items;
        inflater = activity.getWindow().getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
//        return  inflater.inflate(R.layout.row_tweet, parent, false);

        View view = convertView;

        if (view == null){
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.row_tweet, null);
        }

        Tweet tweet  = tweets.get(position);

        if (tweet!=null){
            TextView title = (TextView)view.findViewById(R.id.tweetTitleRow);
            if (title!=null) {
                title.setText(tweet.getTitle());
            }

            TextView body = (TextView)view.findViewById(R.id.tweetBodyRow);
            if (body!=null){
                body.setText(tweet.getBody());
            }

        }

        return  view;
    }
}
