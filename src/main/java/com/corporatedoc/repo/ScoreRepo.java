package com.corporatedoc.repo;

import com.corporatedoc.model.Score;
import com.corporatedoc.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepo extends JpaRepository<Score, Long> {
    List<Score> findAllByOwner_Role(Role role);
}
