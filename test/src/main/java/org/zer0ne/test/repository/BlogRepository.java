package org.zer0ne.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.zer0ne.test.model.Blog;

import java.util.List;
@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByAuthor(int author);
}
