package com.springboot.post.repository;

import com.springboot.post.entity.UserLike;
import com.springboot.post.utils.ActionEnum;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserLikeRepository extends JpaRepository<UserLike, Long> {

    boolean existsByPostIdAndUserIdAndAction(Long postId, Long userId,ActionEnum action);

     @Transactional
    @Modifying
    @Query("DELETE FROM UserLike ul WHERE ul.postId = :postId AND ul.userId = :userId AND ul.action = :action")
    void deleteByPostIdAndUserIdAndAction(@Param("postId") Long postId, @Param("userId") Long userId, @Param("action") ActionEnum action);


}
