package com.demo.splitwise.model;

import lombok.Data;

import java.util.List;

@Data
public class Group {
    private String id;
    private String name;
    private List<User> members;
}
