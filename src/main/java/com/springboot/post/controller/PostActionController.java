package com.springboot.post.controller;

import com.springboot.post.service.PostActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/intuit/api/v1/post/action")
public class PostActionController {

    private PostActionService postActionService;

    public PostActionController(PostActionService postActionService) {
        this.postActionService = postActionService;
    }
    @PostMapping("/{postId}/like/{userId}")
    public ResponseEntity<Void> likePost(@PathVariable Long postId,@PathVariable Long userId) {
        postActionService.likePost(postId,userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{postId}/like/{userId}")
    public ResponseEntity<Void> removeLikeFromPost(@PathVariable Long postId,@PathVariable Long userId) {
        postActionService.removeLikeFromPost(postId,userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{postId}/dislike/{userId}")
    public ResponseEntity<Void> dislikePost(@PathVariable Long postId,@PathVariable Long userId) {
        postActionService.dislikePost(postId,userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{postId}/dislike/{userId}")
    public ResponseEntity<Void> removeDislikeFromPost(@PathVariable Long postId,@PathVariable Long userId) {
        postActionService.removeDislikeFromPost(postId,userId);
        return ResponseEntity.ok().build();
    }
}
