package com.greenatom.testcase.service;

import com.greenatom.testcase.entity.Document;
import com.greenatom.testcase.repository.DocumentRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {

    private final DocumentRepo documentRepo;

    public DocumentService(DocumentRepo documentRepo) {
        this.documentRepo = documentRepo;
    }

    public int addDocument(Document document) {
        documentRepo.save(document);
        return document.getId();
    }
    
    public List<Document> findAll(PageRequest pageRequest) {
        Page<Document> page = documentRepo.findAll(pageRequest);
        return page.getContent();
    }

    public Optional<Document> getDocumentById(int id){
        return documentRepo.findById(id);
    }

    public void deleteDocument(int id){
        documentRepo.deleteById(id);
    }
}
