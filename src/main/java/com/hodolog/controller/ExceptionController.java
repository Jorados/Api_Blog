package com.hodolog.controller;

import com.hodolog.exception.HodologException;
import com.hodolog.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    //@Valid 예외처리
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //@Valid로 인한 메소드 에러가 발생했을때만 이 컨트롤러가 에러를 잡아주게 끔
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse invalidRequestHandler(MethodArgumentNotValidException e){
        ErrorResponse response = ErrorResponse.builder()
                .code("400")
                .message("잘못된 요청입니다.")
                .build();

        //ErrorResponse에 MethodArgumentNotValidException에러(@NotBlank 필드에러)를 추가해서 리턴한다.
        for (FieldError fieldError : e.getFieldErrors()) {
            response.addValidation(fieldError.getField(), fieldError.getDefaultMessage()); //addValidation(어떤필드,프로퍼티에있는 메시지)
        }
        return response;
    }

    //직접만든 예외처리
    @ResponseBody
    @ExceptionHandler(HodologException.class)
    public ResponseEntity<ErrorResponse> jodologException(HodologException e){
        int statusCode = e.getStatusCode();

        ErrorResponse body = ErrorResponse.builder()
                .code(String.valueOf(statusCode))
                .message(e.getMessage())
                .validation(e.getValidation())
                .build();

        //ResponseEntity를 쓰면 status도 챙기고 , 응답 바디도 챙길 수 있다.
        ResponseEntity<ErrorResponse> response = ResponseEntity.status(statusCode)
                .body(body);

        return response;
    }

}
