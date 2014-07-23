package com.example.sonlethanh.hellotwitter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;


public class HelloTwitterMainActivity extends ActionBarActivity {

    Button _loginBtn;
    EditText username; //   = (EditText)findViewById(R.id.txtUsername);
    EditText password; //  = (EditText)findViewById(R.id.txtPassword);
    SharedPreferences prefs ; // = getSharedPreferences("codelearn_twitter", MODE_PRIVATE);
    SharedPreferences.Editor editor; //  = prefs.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{
            SharedPreferences prefs = getSharedPreferences("codelearn_twitter", MODE_PRIVATE);
            String savedUsername = prefs.getString("username", null);
            String savedPassword = prefs.getString("password", null);
            if (savedUsername != null && savedPassword!=null){
                // check if the current activity needs to be skipped
                Intent intent = new Intent(this, TweetListActivity.class);
                startActivity(intent);
                finish();
            }

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_hello_twitter_main);
            username = (EditText)findViewById(R.id.txtUsername);
            password = (EditText)findViewById(R.id.txtPassword);
            _loginBtn = (Button)findViewById(R.id.btnLogin);
            prefs = getSharedPreferences("codelearn_twitter", MODE_PRIVATE);
            editor = prefs.edit();

            _loginBtn.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Log.d("Username caught", username.getText().toString());
                    Log.d("Password caught", password.getText().toString());

                    editor.putString("username", username.getText().toString());
                    editor.putString("password", password.getText().toString());
                    editor.commit();

    //                _loginBtn.setText("I am clicked");
                    Intent intent = new Intent(HelloTwitterMainActivity.this, TweetListActivity.class);
                    startActivity(intent);
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.hello_twitter_main, menu);


        }catch(Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try{
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return super.onOptionsItemSelected(item);
    }
}
