package com.blog.Service;

import com.blog.Model.LoginRequest;
import com.blog.Model.LoginResponse;
import com.blog.Model.TableModel.LoginInfo;
import com.blog.Utils.ResponseStatus;
import com.blog.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginService {

    @Autowired
    LoginRepository loginRepository;

    public LoginService(){

    }
    public LoginResponse validateLogin(LoginRequest request){

        LoginResponse response = new LoginResponse();

        LoginInfo info = loginRepository.findByUsername(request.getUsername());
        if (Objects.nonNull(info) && info.getPassword().equals(request.getPassword())){
            response.setResponseMessage("Validation passed");
            response.setResponseStatus(ResponseStatus.Success);
        }else if (Objects.isNull(info)){
            response.setResponseStatus(ResponseStatus.Failure);
            response.setResponseMessage("User does not exist");
        }else{
            response.setResponseStatus(ResponseStatus.Failure);
            response.setResponseMessage("Password and username not match");
        }
        return response;
    }

}
