package com.blog.Service;

import com.blog.Model.ChangePasswordRequest;
import com.blog.Model.ChangePasswordResponse;
import com.blog.Model.TableModel.LoginInfo;
import com.blog.Utils.ResponseStatus;
import com.blog.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChangePasswordService {

@Autowired
LoginRepository loginRepository;

    public ChangePasswordService(){

    }

    public ChangePasswordResponse changePassword(ChangePasswordRequest request){
        ChangePasswordResponse response = new ChangePasswordResponse();
        LoginInfo info = new LoginInfo();

        try {
            info = loginRepository.findByUsername(request.getUsername());
        } catch (Exception e){
            e.printStackTrace();
        }

        String oldPassword = info.getPassword();
        String inputPassword = request.getOldPassword();
        String newPassword = request.getNewPassword();
        if(!oldPassword.equals(inputPassword)){
            response.setResponseStatus(ResponseStatus.Failure);
            response.setResponseMessage("Password does not match, cannot verify.");
        }

        else if(oldPassword.equals(newPassword)){
            response.setResponseStatus(ResponseStatus.Failure);
            response.setResponseMessage("You cannot use the same password.");
        } else {
            response.setResponseStatus(ResponseStatus.Success);
            response.setResponseMessage("Successfully change password.");
            loginRepository.changePasswordForLogin(newPassword, info.getUsername());
        }

        return response;
    }
}
