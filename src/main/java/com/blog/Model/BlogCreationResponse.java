package com.blog.Model;

import com.blog.Utils.ResponseStatus;
import lombok.Data;

@Data
public class BlogCreationResponse {

    String responseMessage;
    ResponseStatus responseStatus;

}
