package com.greatestsasha.training.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/*
 * The @Document annotation is from Spring Data MongoDB and
 * indicates that this class is a MongoDB document and specifies
 * the collection name where instances of this class will be stored.
 */
@Document(collection = "studentdata")
public class Student {

    @Id
    private String id;
    private String name;
    private String age;
}