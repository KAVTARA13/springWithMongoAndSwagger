package com.example.springwithmongoandswagger.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("JobPost")
@Data
@NoArgsConstructor
public class Post {
    String desc;
    String profile;
    int exp;
    String[] techs;
}
