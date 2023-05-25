package com.demo.splitwise.controller.response;

import lombok.Data;

@Data
public class GenericResponse<T> {

    T result;
    String error;
}
