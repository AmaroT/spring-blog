package com.codeup.blog.repositories;

import com.codeup.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username); // select * from ads where title = ?
    User findFirstByUsername(String username); // select * from ads where title = ? limit 1

    //The following method is equivalent to the build in 'getOne' method, there's no need to create this example
    @Query("from User u where u.id = ?1")
    User getAdById(long id);

    //The following method shows you how to use named parameters in a HQL custom query;
    @Query("FROM User u WHERE u.username LIKE %:term%")
    List<User> searchByTitleLike(@Param("term") String term);
}