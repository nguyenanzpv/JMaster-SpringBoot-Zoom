package com.springboot.project2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder //thay cho new
@NoArgsConstructor
@AllArgsConstructor
//tao kieu tra ve dong nhat cho tat ca cac API
public class ResponseDTO<T> {
    private int status;
    private String error;
    private T data;
}
