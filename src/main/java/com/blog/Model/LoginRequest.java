package com.blog.Model;

import lombok.Data;

@Data
public class LoginRequest {

    String username;
    String password;
    String requestId;

}
