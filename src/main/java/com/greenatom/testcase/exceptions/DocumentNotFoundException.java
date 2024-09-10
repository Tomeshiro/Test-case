package com.greenatom.testcase.exceptions;

public class DocumentNotFoundException extends RuntimeException{
    public DocumentNotFoundException(int id) {
        super("Document with id " + id + " not found");
    }
}
