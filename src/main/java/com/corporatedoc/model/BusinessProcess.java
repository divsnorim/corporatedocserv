package com.corporatedoc.model;

import com.corporatedoc.model.enums.DocumentStatus;
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
public class BusinessProcess {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "businessProcess", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Documents> documents;

    public BusinessProcess(String name) {
        this.name = name;
    }

    public int getSignedCount() {
        return documents.stream().reduce(0, (i, document) -> {
            if (document.getStatus() == DocumentStatus.SIGNED) return i + 1;
            return i;
        }, Integer::sum);
    }

    public int getModCount() {
        return documents.stream().reduce(0, (i, document) -> {
            if (document.getStatus() == DocumentStatus.MODIFICATION) return i + 1;
            return i;
        }, Integer::sum);
    }

    public int getNotSignedCount() {
        return documents.stream().reduce(0, (i, document) -> {
            if (document.getStatus() == DocumentStatus.NOT_SIGNED) return i + 1;
            return i;
        }, Integer::sum);
    }

    public int getDocumentsSize() {
        return documents.size();
    }
}