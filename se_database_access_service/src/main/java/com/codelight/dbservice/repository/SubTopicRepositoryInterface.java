package com.codelight.dbservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codelight.dbservice.model.EntitySubTopic;

@Repository
public interface SubTopicRepositoryInterface extends JpaRepository<EntitySubTopic, Long> {

}
