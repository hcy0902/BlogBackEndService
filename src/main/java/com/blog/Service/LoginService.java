package com.blog.Service;

import com.blog.Model.LoginRequest;
import com.blog.Model.LoginResponse;
import com.blog.Model.TableModel.LoginInfo;
import com.blog.Utils.ResponseStatus;
import com.blog.repository.LoginRepository;
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

    @Autowired
    LoginRepository loginRepository;

    public LoginService(){

    }
    public LoginResponse validateLogin(LoginRequest request){

        LoginResponse response = new LoginResponse();
        LoginInfo info = new LoginInfo();

        // Failure login attempts more than 3 times
        // once successfully login, reset attempts

        // username exist in database, but password wrong
        if(!checkIfUsernameExistInCache(request.getUsername())){
            info = loginRepository.findByUsername(request.getUsername());
            if (Objects.nonNull(info)){
                passWordCache.put(info.getUsername(), info.getPassword());
                loginTimeCache.put(info.getUsername(), 0);
            }
        }else {
            // source of truth
            info.setUsername(request.getUsername());
            info.setPassword(passWordCache.get(request.getUsername()));
        }

        //nullcheck
        if (Objects.nonNull(info) && info.getPassword().equals(request.getPassword())){
            loginTimeCache.put(info.getUsername(), 0);
            response.setResponseMessage("Validation passed");
            response.setResponseStatus(ResponseStatus.Success);
        }else if (Objects.isNull(info)){
            response.setResponseStatus(ResponseStatus.Failure);
            response.setResponseMessage("User does not exist");
        }else{

            if (loginTimeCache.get(info.getUsername()) == 3){
                response.setResponseMessage("Failure attemps more than 3 times, lock account");
                // talk to database to mark the account as locked
            }else{
                loginTimeCache.put(info.getUsername(), loginTimeCache.get(info.getUsername())+1);
                response.setResponseMessage("Password and username not match");
            }
            response.setResponseStatus(ResponseStatus.Failure);
        }
        return response;
    }

    private static boolean checkIfUsernameExistInCache(String userName){
        if(passWordCache.containsKey(userName) && loginTimeCache.containsKey(userName) ){
            return true;
        }
        return false;
    }

}
