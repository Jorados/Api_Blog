package com.hodolog.controller;

import com.hodolog.domain.Post;
import com.hodolog.respository.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@WebMvcTest -> MockMvc에 대한 빈 주입
@AutoConfigureMockMvc //-> 이걸 달아주면 @SpringBootTest랑 사용가능
@SpringBootTest
class PostControllerTest {

    @BeforeEach
    void clean(){
        postRepository.deleteAll();
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("/posts 요청시 hello를 출력한다.")
    void test() throws Exception {

        //글 제목
        //글 내용
        //사용자
            //id
            //user
            //level
        /**
         * keyValue type은 도메인 데이터를 표현하는데 한계가 있다.
         * 그래서 json타입이 좋다
         * title="xxx"&content="xxx"&user.id="xxx"&~~
         */
        /**
         * json type
         * {
         *      "title":"xxx",
         *      "content":"xxx",
         *      "user":{
         *              "id":"xxx",
         *              "name":"xxx"
         *          }
         * }
         */
        //mockMvc는 기본 데이터 전송 타입이 application/Json 타입이다.
        //expected
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다.\"," +
                                " \"content\": null}")
                )
                .andExpect(status().isBadRequest())
                //.andExpect(content().string("{}")) //String 내용으로 등록
                .andDo(print()); //응답값 프린트

    }

    @Test
    @DisplayName("/posts 요청시 title값은 필수다.")
    void test2() throws Exception {

        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": null ,\"content\":null}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요."))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 DB에 값이 저장된다")
    void test3() throws Exception {
        //when
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다.\",\"content\":\"내용입니다.\"}")
                )
                .andExpect(status().isOk())
                .andDo(print());

        //then
        assertThat(postRepository.count()).isEqualTo(1);
        assertThat(postRepository.findAll().get(0).getTitle()).isEqualTo("제목입니다.");

    }

}