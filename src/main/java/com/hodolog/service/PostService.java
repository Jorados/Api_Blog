package com.hodolog.service;

import com.hodolog.domain.Post;
import com.hodolog.domain.PostEditor;
import com.hodolog.request.PostCreate;
import com.hodolog.request.PostEdit;
import com.hodolog.request.PostSearch;
import com.hodolog.response.PostResponse;
import com.hodolog.respository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate){
        //postCreate -> Entity 형태로 만들어 줘야 함
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();
        postRepository.save(post);
    }

    public PostResponse get(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 글 입니다."));

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();
    }

    // 글이 너무 많은 경우 -> 비용이 너무 많이 든다.
    // 글이 -> 1 100,000,000 -> DB글 모두 조회하는 경우 -> DB가 뻗을 수 있다.
    // DB -> 애플리케이션 서버로 전달하는 시간, 트래픽비용 등이 많이 발생할 수 있다.

    public List<PostResponse> getList(PostSearch postSearch) {
        return postRepository.getList(postSearch).stream()
                .map(post ->  new PostResponse(post))
                .collect(Collectors.toList());
    }

    @Transactional
    public void edit(Long id, PostEdit postEdit){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));

        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();

        PostEditor postEditor = editorBuilder
                .title(postEdit.getTitle())
                .content(postEdit.getContent())
                .build();

        post.edit(postEditor);
    }
}
