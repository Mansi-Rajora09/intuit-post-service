package com.springboot.post.utils;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.springboot.post.payload.PostDto;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@Service
public class Validation {

    public void validatecreatePost(@Valid PostDto postDto) {
        validateTitle(postDto.getTitle());
        validateCategoryId(postDto.getCategoryId());
    }

    private void validateCategoryId(Long categoryId) {
        if (categoryId <=0) {
            throw new ValidationException(" categoryId cannot be empty");
        }
    }

    Pattern pattern = Pattern.compile("\\d+");

    public void validateTitle(String title) {
        // Define a regex pattern to match only integers
        if (title == null || title.trim().isEmpty()) {
            throw new ValidationException(" title cannot be empty");
        }

        // Check if the category name contains only integers
        if (pattern.matcher(title).matches()) {
            throw new ValidationException(" title cannot contain only integers");
        }
    }
}