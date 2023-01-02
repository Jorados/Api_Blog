package com.hodolog.respository;


import com.hodolog.domain.Post;
import com.hodolog.domain.QPost;
import com.hodolog.request.PostSearch;
import com.hodolog.service.PostService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.hodolog.domain.QPost.*;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Post> getList(PostSearch postSearch) {
        return queryFactory
                .selectFrom(post)
                .limit(postSearch.getSize())
                .offset(postSearch.getOffset()) //page만큼 건너뛰어서 값 가져온다.
                .orderBy(post.id.desc())
                .fetch();
    }
}
