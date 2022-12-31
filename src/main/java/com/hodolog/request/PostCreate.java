package com.hodolog.request;

import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Data
@NoArgsConstructor
public class PostCreate {

    @NotBlank(message = "타이틀을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @Builder
    //lombok에서 제공해준다. //buidler패턴 숙지하자
    //파라미터 순서가 바뀌는 등 골치 아픈 일을 막아준다.
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void changeTitle(String title){
        this.title = title;
    }

    //만약 도메인 프로퍼티가 final 속성일 경우 위의 changeTitle처럼
    //title을 변경할 수 없다. 그럴 떄 이런 식으로 builer로 바꿔줄수있다.
//    public void changeTitle2(String title){
//        return PostCreate.builder()
//                .title(title)
//                .content(content)
//                .build();
//    }

    //빌더의 장점
    // - 가독성이 좋다. (값 생성에 대한 유연함)
    // - 필요한 값만 받을 수 있다. //-> (생성자) 오버로딩 가능한 조건 찾아보기
    // - 객체의 불변성
}
