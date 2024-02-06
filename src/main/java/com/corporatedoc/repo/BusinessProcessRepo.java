package com.corporatedoc.repo;

import com.corporatedoc.model.BusinessProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessProcessRepo extends JpaRepository<BusinessProcess, Long> {
}
