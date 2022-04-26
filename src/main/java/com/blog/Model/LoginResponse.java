package com.blog.Model;

import com.blog.Utils.ResponseStatus;
import lombok.Data;

@Data
public class LoginResponse {

    String responseMessage;
    ResponseStatus responseStatus;
}
