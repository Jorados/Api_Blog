package com.hodolog.respository;

import com.hodolog.domain.Post;
import com.hodolog.request.PostCreate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>,PostRepositoryCustom {
    Post findByTitle(String title);
}
