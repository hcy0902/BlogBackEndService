package com.blog.Model;

import com.blog.Utils.ResponseStatus;
import lombok.Data;

@Data
public class RegisterResponse {

    String responseMessage;
    ResponseStatus status;
}
