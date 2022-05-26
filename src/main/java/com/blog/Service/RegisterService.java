package com.blog.Service;

import com.blog.Model.RegisterRequest;
import com.blog.Model.RegisterResponse;
import com.blog.Model.TableModel.LoginInfo;
import com.blog.Model.TableModel.UserInfo;
import com.blog.Utils.ResponseStatus;
import com.blog.repository.LoginRepository;
import com.blog.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RegisterService {

    @Autowired
    LoginRepository repository;
    @Autowired
    UserRepository userRepository;
    public RegisterService(){

    }

    public RegisterResponse registerUser(RegisterRequest request){

        RegisterResponse response = new RegisterResponse();

        //confirm username does not exist
        if (Objects.nonNull(repository.findByUsername(request.getUsername()))){
            response.setResponseMessage("Username already exist");
            response.setStatus(ResponseStatus.Failure);
            return response;
        }

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setUsername(request.getUsername());
        loginInfo.setEmail(request.getEmail());
        loginInfo.setPassword(request.getPassword());
        loginInfo.setActive(true);
        repository.save(loginInfo);

        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(request.getEmail());
        userInfo.setUsername(request.getUsername());
        userInfo.setBirthday(request.getBirthday());
        userInfo.setFirstName(request.getFirstName());
        userInfo.setLastName(request.getLastName());
        userInfo.setActive(true);
        userRepository.save(userInfo);


        response.setStatus(ResponseStatus.Success);
        response.setResponseMessage("User registered successfully");
        return response;
    }
}
