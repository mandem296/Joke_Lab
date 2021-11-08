package com.example.joke;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){

        //Create the intent
        Intent intent=new Intent(this,MessageService.class);

        //Add text to the intent
        intent.putExtra(MessageService.EXTRA_MESSAGE,getString(R.string.button_response));

        //Start the service
        startService(intent);

    }


}