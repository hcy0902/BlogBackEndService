package com.blog;

import com.blog.Model.*;
import com.blog.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.View;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@Validated
public class ApplicationController {

    @Autowired
    BlogCreationService blogService;
    @Autowired
    RegisterService registerService;
    @Autowired
    LoginService loginService;
    @Autowired
    DeletePostService deletePostService;

    @Autowired
    ViewPostService viewPostService;

    @Autowired
    ChangePasswordService changePasswordService;

    @Autowired
    UpdateProfileService updateProfileService;

    @PostMapping("/blogCreationService")
    public ResponseEntity<BlogCreationResponse> blogCreation (@Valid @RequestBody BlogCreationRequest blogCreationRequest){

        BlogCreationResponse blogCreationResponse = new BlogCreationResponse();
        blogCreationResponse = blogService.createBlog(blogCreationRequest);

        return ResponseEntity.ok(blogCreationResponse);
    }

    @GetMapping("/test")
    public List<String> blogCreation (){

        return Arrays.asList("Qingfei", "miemao");
    }


    @PostMapping("/registerUser")
    public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody RegisterRequest request){

        RegisterResponse response = registerService.registerUser(request);


        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){

        LoginResponse response = loginService.validateLogin(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/viewPost")
    public ResponseEntity<ViewReponse> viewPosts(@Valid @RequestBody ViewRequest request){
        ViewReponse response = viewPostService.viewPost(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/deletePost")
    public ResponseEntity<DeletePostResponse> deletePost(@Valid @RequestBody DeletePostRequest request){
        DeletePostResponse response = deletePostService.deletePost(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<ChangePasswordResponse> changePassword(@Valid @RequestBody ChangePasswordRequest request){
        ChangePasswordResponse response = changePasswordService.changePassword(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/updateProfile")

    public ResponseEntity<UpdateProfileResponse> updateProfile(@Valid @RequestBody UpdateProfileRequest request){
        UpdateProfileResponse response = updateProfileService.updateProfile(request);

        return ResponseEntity.ok(response);
    }


}
