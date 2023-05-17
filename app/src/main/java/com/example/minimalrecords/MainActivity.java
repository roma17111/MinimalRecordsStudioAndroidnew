package com.example.minimalrecords;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

    public void exit(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Выход");
        builder.setMessage("Вы точно хотите выйти?");
        builder.setCancelable(false);
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.create();
        builder.show();
    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void goToReviews(View view) {
        Intent intent = new Intent(this, Revievs.class);
        startActivity(intent);
    }

    public void sendReview(View view) {
        Intent intent = new Intent(this,SendReview.class);
        startActivity(intent);
    }
}