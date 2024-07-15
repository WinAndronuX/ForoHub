package com.winandronux.forohub.repository;

import com.winandronux.forohub.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitle(String title);
}
