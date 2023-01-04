//package com.hodolog.controller;
//
//
//import com.hodolog.domain.Post;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//
//import javax.annotation.PostConstruct;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@Profile("local")
//@Component
//@RequiredArgsConstructor
//public class initPost {
//
//    private final InitPost initPost;
//
//    @PostConstruct
//    public void init(){
//        initPost.init();
//    }
//
//    @Component
//    static class InitPost{
//        @PersistenceContext
//        private EntityManager em;
//
//        @Transactional
//        public void init(){
//            for(int i=0; i<20; i++) {
//                Post post = Post.builder()
//                        .title("제목" + i)
//                        .content("내용" + i)
//                        .build();
//                em.persist(post);
//            }
//
//        }
//    }
//}
