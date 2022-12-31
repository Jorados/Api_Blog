package com.hodolog.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class PostEditor {
    private String title;
    private String content;

    @Builder
    public PostEditor(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
