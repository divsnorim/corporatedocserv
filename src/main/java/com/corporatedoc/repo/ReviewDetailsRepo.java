package com.corporatedoc.repo;

import com.corporatedoc.model.ReviewDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDetailsRepo extends JpaRepository<ReviewDetails, Long> {
}
