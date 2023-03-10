package com.hodolog.request;

import lombok.*;

@Getter
@Setter
@Builder
public class PostSearch {

    private static final int MAX_SIZE = 2000;

    private Integer page = 1;
    private Integer size = 10;

    public long getOffset(){
        return (long) (Math.max(1,page) - 1) * Math.min(size,MAX_SIZE);
    }
}
