package com.corporatedoc.model;

import com.corporatedoc.model.enums.DocumentDepartment;
import com.corporatedoc.model.enums.DocumentStatus;
import com.corporatedoc.model.enums.DocumentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Documents {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;
    private int number;
    @Enumerated(EnumType.STRING)
    private DocumentType type;
    @Enumerated(EnumType.STRING)
    private DocumentDepartment department;
    @Enumerated(EnumType.STRING)
    private DocumentStatus status;
    private String date;
    private String document;
    @Column(length = 5000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users owner;
    @ManyToOne(fetch = FetchType.LAZY)
    private BusinessProcess businessProcess;
    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<DocumentComments> documentComments;

    public Documents(String name, int number, DocumentType type, DocumentDepartment department, String date, String document, BusinessProcess businessProcess, String description) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.department = department;
        this.date = date;
        this.document = document;
        this.businessProcess = businessProcess;
        this.description = description;
        this.status = DocumentStatus.NOT_SIGNED;
    }

    public void set(String name, int number, DocumentType type, DocumentDepartment department, String date, BusinessProcess businessProcess, String description) {
        this.name = name;
        this.number = number;
        this.type = type;
        this.department = department;
        this.date = date;
        this.businessProcess = businessProcess;
        this.description = description;
    }
}
