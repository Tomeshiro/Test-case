package com.greenatom.testcase.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "content", nullable = false, columnDefinition="TEXT")
    private String content;

    @Column(name = "title", nullable = false)
    private String title;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "creation_date", nullable = false, columnDefinition="TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime creationDate;

    @Column(name = "description", nullable = false)
    private String description;

    public Document(String content, String title, LocalDateTime creationDate, String description) {
        this.content = content;
        this.title = title;
        this.creationDate = creationDate;
        this.description = description;
    }

    public Document(){}

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", creation_date='" + creationDate + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
