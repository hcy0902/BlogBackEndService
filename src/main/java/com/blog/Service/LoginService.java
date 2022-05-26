package com.blog.Service;

import com.blog.Model.LoginRequest;
import com.blog.Model.LoginResponse;
import com.blog.Model.TableModel.LoginInfo;
import com.blog.Utils.ResponseStatus;
import com.blog.repository.LoginRepository;
import com.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginService {

    //username, password
    static Map<String, String> passWordCache = new HashMap();
    //username, loginTime
    static Map<String, Integer> loginTimeCache = new HashMap();

    static Map<String, Boolean> activeCache = new HashMap<>();

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    UserRepository userRepository;

    public LoginService(){

    }
    public LoginResponse validateLogin(LoginRequest request){

        LoginResponse response = new LoginResponse();
        LoginInfo info = new LoginInfo();

        if(!checkIfUsernameExistInCache(request.getUsername())){
            info = loginRepository.findByUsername(request.getUsername());
            if (Objects.nonNull(info)){
                passWordCache.put(info.getUsername(), info.getPassword());
                loginTimeCache.put(info.getUsername(), 0);
                activeCache.put(info.getUsername(), info.isActive());
            }
        }else {
            info.setUsername(request.getUsername());
            info.setPassword(passWordCache.get(request.getUsername()));
            info.setActive(activeCache.get(request.getUsername()));
        }


        if (Objects.isNull(info)){
            response.setResponseStatus(ResponseStatus.Failure);
            response.setResponseMessage("User does not exist");
        }else if (!info.isActive()){
            response.setResponseMessage("Account is locked");
            response.setResponseStatus(ResponseStatus.Failure);
        }else if (info.getPassword().equals(request.getPassword())){
            loginTimeCache.put(info.getUsername(), 0);
            response.setResponseMessage("Validation passed");
            response.setResponseStatus(ResponseStatus.Success);
        } else{

            if (loginTimeCache.get(info.getUsername()) == 3){
                response.setResponseMessage("Failure attemps more than 3 times, lock account");
                loginRepository.setActiveStatusForLogin(false, info.getUsername());
                userRepository.setActiveStatusForUser(false, info.getUsername());
                activeCache.put(info.getUsername(), false);
            }else{
                loginTimeCache.put(info.getUsername(), loginTimeCache.get(info.getUsername())+1);
                response.setResponseMessage("Password and username not match");
            }
            response.setResponseStatus(ResponseStatus.Failure);
        }
        return response;
    }

    private static boolean checkIfUsernameExistInCache(String userName){
        if(passWordCache.containsKey(userName) && loginTimeCache.containsKey(userName)){
            return true;
        }
        return false;
    }

}
