package com.greenatom.testcase.repository;

import com.greenatom.testcase.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepo extends JpaRepository<Document, Integer> {

}
