package com.example.springwithmongoandswagger.repository;

import com.example.springwithmongoandswagger.model.Post;

import java.util.List;

public interface SearchRepository {
    List<Post> findByText(String text);
}
