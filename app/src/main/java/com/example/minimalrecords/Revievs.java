package com.example.minimalrecords;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minimalrecords.models.Reviews;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Revievs extends AppCompatActivity {
    private FirebaseListAdapter<Reviews> adapter;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("reviews");

    RelativeLayout root;
    private ListView coursesLV;

    // creating a new array list.
    ArrayList<String> coursesArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revievs);
        coursesLV = findViewById(R.id.list_of_messages);
        coursesArrayList = new ArrayList<String>();
        start1();
    }

    public void start1() {
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, coursesArrayList);
            myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    coursesArrayList.add(snapshot.getValue(Reviews.class).toString());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            coursesLV.setAdapter(adapter);
    }

    public void startReviews(View view) {
        openWiews();
    }

    private void openWiews() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Напишите отзыв");
        LayoutInflater inflater = LayoutInflater.from(this);
        View fragmentReview = inflater.inflate(R.layout.fragment_reviovs, null);
        builder.setView(fragmentReview);

        builder.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        builder.setPositiveButton("Отправить", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EditText name10 = (EditText) fragmentReview.findViewById(R.id.name1);
                EditText review10 = (EditText) fragmentReview.findViewById(R.id.description1);


                String nameS = name10.getText().toString();
                String reviewS = review10.getText().toString();
                if (TextUtils.isEmpty(nameS)){
                    dialogInterface.dismiss();
                    Toast.makeText(Revievs.this, "Все поля должны быть заполнены!!!)",
                            Toast.LENGTH_LONG).show();
                }else if (TextUtils.isEmpty(reviewS)) {
                    dialogInterface.dismiss();
                    Toast.makeText(Revievs.this, "Все поля должны быть заполнены!!!)",
                            Toast.LENGTH_LONG).show();
                }
                Reviews review = new Reviews(nameS, reviewS);
                myRef.child(review.getReview())
                        .setValue(review).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void v) {
                                Toast.makeText(Revievs.this, "Cпасибо за ваш отзыв:)",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Revievs.this,
                                        "Ошибка отправки",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
        builder.create();
        builder.show();
    }

}




