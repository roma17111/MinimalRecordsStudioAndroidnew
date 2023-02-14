package com.example.minimalrecords;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.minimalrecords.models.Reviews;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class SendReview extends AppCompatActivity {
    private EditText courseNameEdt, courseDurationEdt, courseDescriptionEdt;
    private Button submitCourseBtn;
    private String courseName,  courseDescription;
    private Integer id = 0;
    FirebaseDatabase database12 = FirebaseDatabase.getInstance();
    DatabaseReference myRef12 = database12.getReference("reviews");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_review);
        Random random = new Random();
        id = random.nextInt() * 3000000;
        courseNameEdt = findViewById(R.id.idEdtCourseName1);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription1);
        submitCourseBtn = findViewById(R.id.submitCourse1);
        submitCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseName = courseNameEdt.getText().toString();
                courseDescription = courseDescriptionEdt.getText().toString();
                if (TextUtils.isEmpty(courseName)) {
                    courseNameEdt.setError("Пожалуйста введите имя");
                } else if (TextUtils.isEmpty(courseDescription)) {
                    courseDescriptionEdt.setError("Пожалуйста оставьте отзыв");
                }  else {
                    addDataToFirestore(courseName, courseDescription);
                }
            }
        });
    }

    private void addDataToFirestore(String courseName, String courseDescription) {

        Reviews user = new Reviews(courseName, courseDescription);
        myRef12.child(String.valueOf(id))
                .setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(SendReview.this, "Спасибо за отзыв",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SendReview.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SendReview.this,
                                "Ошибка отправки",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}