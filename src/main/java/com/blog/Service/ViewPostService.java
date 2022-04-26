package com.blog.Service;

import com.blog.Model.TableModel.PostContent;
import com.blog.Model.ViewReponse;
import com.blog.Model.ViewRequest;
import com.blog.Utils.ResponseStatus;
import com.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViewPostService {

    @Autowired
    PostRepository repository;
    public ViewPostService(){

    }

    public ViewReponse viewPost(ViewRequest request){

        List<String> posts = new ArrayList();
        List<PostContent> postContents = repository.findByUsername(request.getUsername());
        for (PostContent content : postContents){
            posts.add(content.getContent());
        }

        ViewReponse response = new ViewReponse();
        response.setPostInfo(postContents);
        response.setPosts(posts);
        response.setResponseMessage("Retrieved posts successfully");
        response.setStatus(ResponseStatus.Success);
        return response;
    }


}
