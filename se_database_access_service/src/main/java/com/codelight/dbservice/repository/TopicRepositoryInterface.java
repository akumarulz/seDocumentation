package com.codelight.dbservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codelight.dbservice.model.EntityTopic;

@Repository
public interface TopicRepositoryInterface extends JpaRepository<EntityTopic, Long> {

}
