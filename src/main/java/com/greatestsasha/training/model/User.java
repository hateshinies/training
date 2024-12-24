package com.greatestsasha.training.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userdata")
@Data
public class User {
    private String username;
}