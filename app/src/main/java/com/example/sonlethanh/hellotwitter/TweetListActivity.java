package com.example.sonlethanh.hellotwitter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sonlethanh.hellotwitter.R;
import com.example.sonlethanh.hellotwitter.model.Tweet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import de.svenjacobs.loremipsum.LoremIpsum;

public class TweetListActivity extends ListActivity {

    private ListView tweetListView;
    private String[] stringArray;
    private ArrayAdapter tweetItemArrayAdapter;
    private List<Tweet> tweets = new ArrayList<Tweet>();
    private static  final  String FILENAME = "tweet_cache.ser";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FileOutputStream fos =null;
        ObjectOutputStream oos =null;
        FileInputStream fis =null;
        ObjectInputStream ois = null;

         LoremIpsum loremIpsum = new LoremIpsum();



        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_tweet_list);

            fis = openFileInput(FILENAME);
            ois = new ObjectInputStream(fis);
            /** calculate number of items **/
            int line_count = 0;
            List<Tweet> tweetsRead =  new ArrayList<Tweet>();
            while( ois.available() > 0) // check if the file stream is at the end
            {
                Tweet tweet = (Tweet)ois.readObject();    // read from the object stream,
                tweetsRead.add(tweet);
                //    which wraps the file stream
                line_count++;
            }

//            List<Tweet> tweetsRead =  (List<Tweet>) ois.readObject();
            // List<Tweet> tweetsRead =  (List<Tweet>) ois.readObject();

            List<Tweet> tweetsWrite = new ArrayList<Tweet>();

            for (int i = 0; i < 20; i++) {
                Tweet tweet = new Tweet();
                tweet.setTitle(loremIpsum.getWords(16));
                tweet.setBody(loremIpsum.getParagraphs(160));
                tweetsWrite.add(tweet);
            }

             fos = openFileOutput(FILENAME, MODE_PRIVATE);
             oos = new ObjectOutputStream(fos);
            oos.writeObject(tweetsWrite);
            oos.close();
            fos.close();

//        stringArray = new String[10];
//        for(int i=0; i<stringArray.length; i++){
//            stringArray[i] = "String " +i;
//        }
//        tweetItemArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
//                stringArray);
//        tweetItemArrayAdapter = new TweetAdapter(this, stringArray);
//        tweetItemArrayAdapter = new TweetAdapter(this, new String[10]);
            tweetItemArrayAdapter = new TweetAdapter(this, tweetsRead);

//        tweetListView = (ListView)findViewById(R.id.tweetList);
//        tweetListView.setAdapter((tweetItemArrayAdapter));
            setListAdapter(tweetItemArrayAdapter);
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException  e){
            e.printStackTrace();
        }catch(Exception  e){
            e.printStackTrace();
        }finally {
            try{
                if (fos!=null)
                    fos.close();
                if (oos!=null)
                    oos.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected  void onListItemClick(ListView l, View v, int position, long id){
//        TextView t = (TextView)v.findViewById(R.id.tweetTitle);
//        t.setText("Tweet Clicked");

        Intent intent = new Intent(this, TweetDetailActivity.class);
        startActivity(intent);
    }

}
