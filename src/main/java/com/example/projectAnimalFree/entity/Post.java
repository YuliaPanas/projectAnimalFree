package com.example.projectAnimalFree.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "posts")
@Where (clause = "removed = false")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_name")
    private String postName;

    @Column(name = "text")
    private String text;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_liked")
    private boolean isLiked = true;

    private boolean available = false;

    private  boolean removed;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", postName='" + postName + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
