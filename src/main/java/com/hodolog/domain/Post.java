package com.hodolog.domain;

import com.hodolog.request.PostEdit;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;
    @Lob
    private String content;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    //PostEditor를 만들어서 빌더패턴으로 set해준다
    public PostEditor.PostEditorBuilder toEditor(){
        return PostEditor.builder()
                .title(title)
                .content(content);
    }

    //이렇게 Editor를 만들어서 수정을 하게되면 파라미터 하나로
    //원하는 필드 여러개를 수정이 가능하다
    public void edit(PostEditor postEditor) {
        title = postEditor.getTitle();
        content = postEditor.getContent();
    }
}
