package com.example.fdb2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void vypisData(View view)
    {
        startActivity(new Intent(this, DatabaseView.class));
    }

    public void vytvorNovyZaznam(View view)
    {
        //new CreateUser();
        Intent intent = new Intent(this, DatabaseWriter.class);
        startActivity(intent);
    }
}