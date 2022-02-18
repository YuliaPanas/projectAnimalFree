package com.example.projectAnimalFree.repository;

import com.example.projectAnimalFree.dto.UserDto;
import com.example.projectAnimalFree.entity.PostLike;
import com.example.projectAnimalFree.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike,Long>, JpaSpecificationExecutor<User> {

    long countByPost_IdAndLikedIsTrue(Long postId);


    Optional<PostLike> findByUser_IdAndPost_Id(long userId, long postId);

    @Query(value = "select pl.users_id from post_like pl where pl.post_id=:q and pl.liked = true", nativeQuery = true)
    List <Long> findUsersNativeQuery(@Param("q") Long postId);


//    List<UserDto> findUsers(long postId);
}
