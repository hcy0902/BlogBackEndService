package com.blog.Model;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    String username;
    String email;
    String phoneNumber;
    String birthday;
    String firstName;
    String lastName;
}
