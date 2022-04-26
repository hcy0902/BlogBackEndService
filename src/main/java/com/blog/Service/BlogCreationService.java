package com.blog.Service;

import com.blog.Model.BlogCreationRequest;
import com.blog.Model.BlogCreationResponse;
import com.blog.Model.TableModel.PostContent;
import com.blog.Model.TableModel.UserInfo;
import com.blog.Utils.ResponseStatus;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class BlogCreationService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    public BlogCreationService(){

    }
    public BlogCreationResponse createBlog(BlogCreationRequest blogCreationRequest){
        BlogCreationResponse blogCreationResponse = new BlogCreationResponse();
        if (Objects.isNull(userRepository.findByUsername(blogCreationRequest.getUsername()))){
            blogCreationResponse.setResponseMessage("User does not exist");
            blogCreationResponse.setResponseStatus(ResponseStatus.Failure);
        }
        else{
            PostContent postContent = new PostContent();
            postContent.setContent(blogCreationRequest.getPostContent());
            postContent.setCreatedByUser(blogCreationRequest.getFirstName() + " " + blogCreationRequest.getLastName());
            postContent.setUsername(blogCreationRequest.getUsername());
            postRepository.save(postContent);
            blogCreationResponse.setResponseMessage("Blog is created successfully");
            blogCreationResponse.setResponseStatus(ResponseStatus.Success);
        }


        return blogCreationResponse;
    }
}
