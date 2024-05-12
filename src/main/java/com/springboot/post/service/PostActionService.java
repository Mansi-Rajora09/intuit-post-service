package com.springboot.post.service;

public interface PostActionService {
    public void likePost(Long postId,Long userId);

    public void removeLikeFromPost(Long postId,Long userId);

    public void dislikePost(Long postId,Long userId);

    public void removeDislikeFromPost(Long postId,Long userId);

}
