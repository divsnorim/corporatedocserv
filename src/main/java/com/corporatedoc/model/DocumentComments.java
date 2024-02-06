package com.corporatedoc.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class DocumentComments {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String date;
    @Column(length = 5000)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private Documents document;
    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;

    public DocumentComments(String text, Documents document, Users owner) {
        this.date = LocalDateTime.now().toString().substring(0, 10);
        this.text = text;
        this.document = document;
        this.owner = owner;
    }
}
