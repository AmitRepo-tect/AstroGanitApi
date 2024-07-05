package com.astroganit.api.repository;

import com.astroganit.api.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
}
