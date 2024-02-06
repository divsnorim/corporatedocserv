package com.corporatedoc.repo;

import com.corporatedoc.model.Documents;
import com.corporatedoc.model.enums.DocumentDepartment;
import com.corporatedoc.model.enums.DocumentStatus;
import com.corporatedoc.model.enums.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentsRepo extends JpaRepository<Documents, Long> {
    List<Documents> findAllByDateAndStatusAndDepartmentAndType(String date, DocumentStatus documentStatus, DocumentDepartment department, DocumentType type);

    List<Documents> findAllByStatusAndDepartmentAndType(DocumentStatus documentStatus, DocumentDepartment department, DocumentType type);
}
