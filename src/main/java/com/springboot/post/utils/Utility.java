package com.springboot.post.utils;

import org.springframework.stereotype.Service;

import com.springboot.post.entity.Post;
import com.springboot.post.payload.PostDto;

@Service
public class Utility {
    
public void updatePostInfo(PostDto postDto, Post post) {
    if(postDto.getTitle()!=null||postDto.getTitle().isEmpty()){
        post.setTitle(postDto.getTitle());
    }
    if(postDto.getDescription()!=null ||postDto.getDescription().isEmpty()){
        post.setDescription(postDto.getDescription());
    }
    if(postDto.getContent()!=null ||postDto.getContent().isEmpty()) {
        post.setContent(postDto.getContent());
    }
}
}