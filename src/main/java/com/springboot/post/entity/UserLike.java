package com.springboot.post.entity;

import lombok.*;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.springboot.post.utils.ActionEnum;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "user_likes")
public class UserLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id",nullable=false)
    private Long userId;

    @Column(name = "post_id" ,nullable=false)
    private Long postId;

    @Enumerated(EnumType.STRING)
    @Column(name = "action" ,nullable=false)
    private ActionEnum action;

    public UserLike(Long postId, Long userId,ActionEnum action) {
        this.postId=postId;
        this.userId=userId;
        this.action=action;
            }

    @Column(name = "created_at", nullable = true, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;           
}
