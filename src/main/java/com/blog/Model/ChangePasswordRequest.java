package com.blog.Model;


import lombok.Data;

@Data
public class ChangePasswordRequest {
    String username;
    String oldPassword;
    String newPassword;
}
