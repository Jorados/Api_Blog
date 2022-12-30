package com.hodolog.service;

import com.hodolog.domain.Post;
import com.hodolog.request.PostCreate;
import com.hodolog.respository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate){
        //postCreate -> Entity 형태로 만들어 줘야 함
        Post post = new Post();
        post.setTitle(postCreate.getTitle());
        post.setContent(postCreate.getContent());
        postRepository.save(post);
    }
}
