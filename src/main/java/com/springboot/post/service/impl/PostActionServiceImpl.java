package com.springboot.post.service.impl;

import com.springboot.post.entity.Post;
import com.springboot.post.entity.UserLike;
import com.springboot.post.exception.ResourceNotFoundException;
import com.springboot.post.repository.PostRepository;
import com.springboot.post.repository.UserLikeRepository;
import com.springboot.post.service.PostActionService;
import com.springboot.post.utils.ActionEnum;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PostActionServiceImpl implements PostActionService {

    private PostRepository postRepository;
    private UserLikeRepository userLikeRepository;

    public PostActionServiceImpl(PostRepository postRepository,UserLikeRepository userLikeRepository) {
        this.postRepository = postRepository;
        this.userLikeRepository=userLikeRepository;
    }

    @Transactional
    public void likePost(Long postId,Long userId) {
    if (!userHasLikedPost(postId, userId)) {
        // Increment like count for the post
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", postId));
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);

        // Record user's like for the post
        UserLike userLike = new UserLike( postId, userId,ActionEnum.LIKE);
        userLikeRepository.save(userLike);
    }
}

    @Transactional
    public void removeLikeFromPost(Long postId, Long userId) {
        if (userLikeRepository.existsByPostIdAndUserIdAndAction(postId, userId,ActionEnum.LIKE)) {
            Post post = getPostById(postId);
            if (post.getLikes() > 0) {
                post.setLikes(post.getLikes() - 1);
                postRepository.save(post);
                userLikeRepository.deleteByPostIdAndUserIdAndAction(postId, userId, ActionEnum.LIKE);
            }
        } else {
            throw new ResourceNotFoundException("Post", "id", postId);
        }
    }


    @Transactional
    public void dislikePost(Long postId,Long userId) {
    if (!userHasDisLikedPost(postId, userId)) {
        // Increment like count for the post
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", postId));
        post.setDislikes(post.getDislikes() + 1);
        postRepository.save(post);

        // Record user's like for the post
        UserLike userLike = new UserLike( postId, userId,ActionEnum.DISLIKE);
        userLikeRepository.save(userLike);
    }
}

@Transactional
public void removeDislikeFromPost(Long postId, Long userId) {
    if (userLikeRepository.existsByPostIdAndUserIdAndAction(postId, userId,ActionEnum.DISLIKE)) {
        Post post = getPostById(postId);
        if (post.getDislikes() > 0) {
            post.setDislikes(post.getDislikes() - 1);
            postRepository.save(post);
            userLikeRepository.deleteByPostIdAndUserIdAndAction(postId, userId, ActionEnum.DISLIKE);
        }
    } else {
        throw new ResourceNotFoundException("Post", "id", postId);
    }
}

    private Post getPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
    }

    private boolean userHasLikedPost(Long postId, Long userId) {
        return userLikeRepository.existsByPostIdAndUserIdAndAction(postId, userId,ActionEnum.LIKE);
    }

    private boolean userHasDisLikedPost(Long postId, Long userId) {
        return userLikeRepository.existsByPostIdAndUserIdAndAction(postId, userId,ActionEnum.DISLIKE);
    }
}