package com.codeup.blog.repositories;

import com.codeup.blog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad findByTitle(String title); // select * from ads where title = ?
    Ad findFirstByTitle(String title); // select * from ads where title = ? limit 1

    // The following method is equivalent to the built in `getOne` method, there's no need to create this example
    @Query("FROM Ad a WHERE a.id = ?1")
    Ad getAdById(long id);

    // The following method shows you how to use named parameters in a HQL custom query:
    @Query("FROM Ad a WHERE a.title LIKE %:term%")
    List<Ad> searchByTitleLike(@Param("term") String term);

    // The following method shows you how to use named parameters in a HQL custom query:
    @Query("FROM Ad a WHERE a.description LIKE %:term%")
    List<Ad> searchByDescriptionLike(@Param("term") String term);
}