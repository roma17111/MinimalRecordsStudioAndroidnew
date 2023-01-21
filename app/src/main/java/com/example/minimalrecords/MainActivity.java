package com.example.minimalrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToUs(View view) {
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }
    public void goToPhoto(View view) {
        Intent intent = new Intent(this, Photo.class);
        startActivity(intent);
    }
    public void goToBuild(View view) {
        Intent intent = new Intent(this, Build.class);
        startActivity(intent);
    }
    public void goToApparatura(View view) {
        Intent intent = new Intent(this, Apparatura.class);
        startActivity(intent);
    }
    public void goToPortfolio(View view) {
        Intent intent = new Intent(this, Portfolio.class);
        startActivity(intent);
    }
}