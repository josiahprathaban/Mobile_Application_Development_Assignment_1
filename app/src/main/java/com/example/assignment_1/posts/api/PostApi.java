package com.example.assignment_1.posts.api;

import com.example.assignment_1.posts.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostApi {

    @GET("/posts")
    Call<List<Post>> getPosts();
}