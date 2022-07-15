package com.blog.Model;

import com.blog.Utils.ResponseStatus;
import lombok.Data;

@Data
public class UpdateProfileResponse {

    String responseMessage;
    ResponseStatus responseStatus;
}
