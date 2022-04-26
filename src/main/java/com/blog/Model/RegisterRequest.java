package com.blog.Model;

import lombok.Data;
import lombok.NonNull;

@Data
public class RegisterRequest {

    @NonNull
    String username;
    @NonNull
    String password;
    @NonNull
    String email;
    String birthday;
    @NonNull
    String phone;
    @NonNull
    String firstName;
    @NonNull
    String lastName;

}
