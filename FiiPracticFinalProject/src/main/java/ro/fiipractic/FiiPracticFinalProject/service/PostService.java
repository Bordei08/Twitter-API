package ro.fiipractic.FiiPracticFinalProject.service;

import ro.fiipractic.FiiPracticFinalProject.models.Post;

import java.util.List;

public interface PostService {
    public void addPost(Post post);

    public void updatePost(String id, String message);

    public void deletePost(String id);

    public List<Post> getFeed(String userId);
    public List<Post> getAllPostByUserId(String userId);
    public Post getPostById(String id);
}