package com.example.minimalrecords;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.minimalrecords.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    // creating variables for our edit text
    private EditText courseNameEdt, courseDurationEdt, courseDescriptionEdt;

    private Button submitCourseBtn;

    private String courseName, courseDuration, courseDescription;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseDescriptionEdt = findViewById(R.id.idEdtCourseDescription);
        courseDurationEdt = findViewById(R.id.idEdtCourseDuration);
        submitCourseBtn = findViewById(R.id.idBtnSubmitCourse);


        submitCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                courseName = courseNameEdt.getText().toString();
                courseDescription = courseDescriptionEdt.getText().toString();
                courseDuration = courseDurationEdt.getText().toString();

                if (TextUtils.isEmpty(courseName)) {
                    courseNameEdt.setError("Пожалуйста введите имя");
                } else if (TextUtils.isEmpty(courseDescription)) {
                    courseDescriptionEdt.setError("Пожалуйста введите описание");
                } else if (TextUtils.isEmpty(courseDuration)) {
                    courseDurationEdt.setError("Пожалуйста введите номер телефона");
                } else {
                    addDataToFirestore(courseName, courseDuration, courseDescription);
                }
           }
        });
    }

    private void addDataToFirestore(String courseName, String courseDuration, String courseDescription) {


        User user = new User(courseName,courseDuration,courseDescription);
        myRef.child(user.getNumber())
                .setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Register.this, "Заявка отправлена. "+ '\n' +
                                        "С вами свяжутся в ближайшее время",
                                Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Register.this,
                                "Ошибка отправки",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}