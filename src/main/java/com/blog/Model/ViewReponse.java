package com.blog.Model;

import com.blog.Model.TableModel.PostContent;
import com.blog.Utils.ResponseStatus;
import lombok.Data;

import java.util.List;

@Data
public class ViewReponse {
    String responseMessage;
    ResponseStatus status;

    List<String> posts;
    List<PostContent> postInfo;
}
