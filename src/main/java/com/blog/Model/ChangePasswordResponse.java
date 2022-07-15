package com.blog.Model;

import com.blog.Utils.ResponseStatus;
import lombok.Data;

@Data
public class ChangePasswordResponse {
    String responseMessage;
    ResponseStatus responseStatus;
}
