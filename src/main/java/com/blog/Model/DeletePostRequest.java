package com.blog.Model;

import lombok.Data;

@Data
public class DeletePostRequest {

    String username;
    long contentId;

}
