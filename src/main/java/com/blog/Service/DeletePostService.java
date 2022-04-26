package com.blog.Service;

import com.blog.Model.DeletePostRequest;
import com.blog.Model.DeletePostResponse;
import com.blog.Model.TableModel.PostContent;
import com.blog.Utils.ResponseStatus;
import com.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeletePostService {

    @Autowired
    PostRepository repository;
    public DeletePostService(){

    }

    public DeletePostResponse deletePost(DeletePostRequest request){
        DeletePostResponse response = new DeletePostResponse();

        List<PostContent> posts = repository.findByUsername(request.getUsername());

        boolean hasPost = false;
        for (PostContent post: posts){
            if (post.getContentId() == request.getContentId()){
                hasPost = true;
                break;
            }
        }

        if (hasPost){
            try{
                repository.deleteById(request.getContentId());

            }catch(Exception e){

            }
            response.setResponseMessage("Deletion completed");
            response.setStatus(ResponseStatus.Success);
        }else{
            response.setResponseMessage("No post exist");
            response.setStatus(ResponseStatus.Failure);
        }

        return response;
    }
}
