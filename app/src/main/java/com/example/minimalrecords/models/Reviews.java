package com.example.minimalrecords.models;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Reviews {

    private String name;
    private long messageTime;
    private String review;



    public Reviews() {
    }

    public Reviews(String name, String review) {
        setName(name);
        setReview(review);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            this.name = "name";
        } else {
            this.name = name;
        }
    }


    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        if (review.isEmpty()) {
            this.review= "reviev";
        } else {
            this.review = review;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reviews reviews = (Reviews) o;
        return name.equals(reviews.name) && review.equals(reviews.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, review);
    }

    @Override
    public String toString() {
        return
                name + '\n' +
                        review + '\n' +"\n";
    }
}
