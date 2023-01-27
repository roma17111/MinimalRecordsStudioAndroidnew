package com.example.minimalrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Photo extends AppCompatActivity {

    private ViewFlipper myViewFlipper;
    private float initialXPoint;
    int[] image = { R.drawable.page1, R.drawable.page2, R.drawable.page5,
             R.drawable.page7, R.drawable.page8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        myViewFlipper = (ViewFlipper) findViewById(R.id.myflipper);
        Toast.makeText(this,
                "Листайте фото свайпами вправо и влево",
                Toast.LENGTH_LONG).show();

        for (int i = 0; i < image.length; i++) {
            ImageView imageView = new ImageView(Photo.this);
            imageView.setImageResource(image[i]);
            myViewFlipper.addView(imageView);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                initialXPoint = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float finalx = event.getX();
                if (initialXPoint > finalx) {
                    if (myViewFlipper.getDisplayedChild() == image.length)
                        break;
                    myViewFlipper.showNext();
                } else {
                    if (myViewFlipper.getDisplayedChild() == 0)
                        break;
                    myViewFlipper.showPrevious();
                }
                break;
        }
        return false;
    }

}