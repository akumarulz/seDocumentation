package com.codelight.dbservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codelight.dbservice.model.EntityArchiveRecord;

@Repository
public interface ArchiveRespositoryInterface extends JpaRepository<EntityArchiveRecord, Long> {

}
