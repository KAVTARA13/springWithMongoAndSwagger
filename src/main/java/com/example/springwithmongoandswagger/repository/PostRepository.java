package com.example.springwithmongoandswagger.repository;

import com.example.springwithmongoandswagger.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {

}
