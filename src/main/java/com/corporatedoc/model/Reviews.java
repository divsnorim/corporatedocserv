package com.corporatedoc.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Reviews {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(length = 5000)
    private String review;
    private String date;
    @OneToOne(fetch = FetchType.LAZY)
    private Users owner;
    @OneToMany(mappedBy = "reviews", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReviewDetails> details;

    public Reviews(String review, String date, Users owner) {
        this.review = review;
        this.date = date;
        this.owner = owner;
        this.details = new ArrayList<>();
    }

    public void addDetail(ReviewDetails detail) {
        this.details.add(detail);
        detail.setReviews(this);
    }
}
