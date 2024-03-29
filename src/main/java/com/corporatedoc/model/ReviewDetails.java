package com.corporatedoc.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ReviewDetails {
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
    @ManyToOne(fetch = FetchType.LAZY)
    private Reviews reviews;

    public ReviewDetails(String review, String date, Users owner) {
        this.review = review;
        this.date = date;
        this.owner = owner;
    }
}
