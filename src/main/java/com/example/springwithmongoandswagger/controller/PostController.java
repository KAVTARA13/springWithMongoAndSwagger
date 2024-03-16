package com.example.springwithmongoandswagger.controller;

import com.example.springwithmongoandswagger.model.Post;
import com.example.springwithmongoandswagger.repository.PostRepository;
import com.example.springwithmongoandswagger.repository.SearchRepositoryImpl;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class PostController {

    private final PostRepository postRepository;
    private final SearchRepositoryImpl searchRepository;

    public PostController(PostRepository postRepository, SearchRepositoryImpl searchRepository) {
        this.postRepository = postRepository;
        this.searchRepository = searchRepository;
    }

    @Hidden
    @RequestMapping("/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text) {
        return searchRepository.findByText(text);
    }


}
