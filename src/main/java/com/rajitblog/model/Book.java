package com.rajitblog.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private String id;
    private String name;

    public Book(String name) {
        this.name = name;
    }
}
