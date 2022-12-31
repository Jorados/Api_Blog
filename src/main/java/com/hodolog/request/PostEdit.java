package com.hodolog.request;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostEdit {

    @NotBlank
    private String title;
    @NotBlank
    private String content;

    @Builder
    public PostEdit(String title,String content) {
        this.title = title;
        this.content = content;
    }
}
