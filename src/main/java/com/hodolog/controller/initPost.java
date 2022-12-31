package com.hodolog.controller;


import com.hodolog.domain.Post;
import com.hodolog.domain.PostEditor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Profile("local")
@Component
@RequiredArgsConstructor
public class initPost {

    private final InitPost initPost;

    @PostConstruct
    public void init(){
        initPost.init();
    }

    @Component
    static class InitPost{
        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init(){
            Post post1 = Post.builder()
                    .title("제목1")
                    .content("내용1")
                    .build();
            em.persist(post1);

            Post post2 = Post.builder()
                    .title("제목2")
                    .content("내용2")
                    .build();
            em.persist(post2);
        }
    }
}
