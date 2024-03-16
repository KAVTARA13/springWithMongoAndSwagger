package com.example.springwithmongoandswagger.repository;

import com.example.springwithmongoandswagger.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import org.bson.Document;

import java.util.List;

@Component
public class SearchRepositoryImpl implements SearchRepository {


    private final MongoClient client;
    private final MongoConverter converter;

    public SearchRepositoryImpl(MongoClient client, MongoConverter converter) {
        this.client = client;
        this.converter = converter;
    }

    @Override
    public List<Post> findByText(String text) {

        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("joblisting");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("index", "default").append("text", new Document("query", text)
                                                .append("path", Arrays.asList("techs", "desc")))),
                new Document("$sort", new Document("exp", 1L)), new Document("$limit", 1L)));

        result.forEach(document -> posts.add(converter.read(Post.class,document)));

        return posts;
    }
}
