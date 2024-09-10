package com.greenatom.testcase.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenatom.testcase.entity.Document;
import com.greenatom.testcase.exceptions.DocumentNotFoundException;
import com.greenatom.testcase.service.DocumentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {


    private final ObjectMapper objectMapper;
    private final DocumentService service;

    @PostMapping("/api")
    public int addDocument(@RequestBody Document document) {
        return service.addDocument(document);
    }

    @SneakyThrows
    @GetMapping("/api/all")
    public String getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
         List<Document> docs = service.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "creationDate")));
         return objectMapper.writeValueAsString(docs);
    }

    @GetMapping("/api")
    public Document getDocument(@RequestParam int id){
        return service.getDocumentById(id)
                .orElseThrow(() -> new DocumentNotFoundException(id));
    }

    @DeleteMapping("/api")
    public void deleteDocument(@RequestParam int id){
        service.deleteDocument(id);
    }
}
