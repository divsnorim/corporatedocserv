package com.corporatedoc.repo;

import com.corporatedoc.model.Apps;
import com.corporatedoc.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppsRepo extends JpaRepository<Apps, Long> {
    List<Apps> findAllByOwner(Users owner);

}
