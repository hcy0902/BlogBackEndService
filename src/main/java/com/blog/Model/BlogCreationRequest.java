package com.blog.Model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BlogCreationRequest {

    String username;
    String postContent;
    @NotNull
    String firstName;
    @NotNull
    String lastName;
    @NotNull
    String requestId;

}
