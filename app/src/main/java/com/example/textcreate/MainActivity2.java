package com.example.textcreate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
    Button start;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        start=(Button) findViewById(R.id.startbtn);
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(start)){
            Intent i=new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}