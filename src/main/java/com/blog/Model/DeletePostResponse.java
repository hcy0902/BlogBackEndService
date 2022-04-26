package com.blog.Model;

import com.blog.Utils.ResponseStatus;
import lombok.Data;
import org.apache.coyote.Response;

@Data
public class DeletePostResponse {

    String responseMessage;
    ResponseStatus status;
}
