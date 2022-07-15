package com.blog.Service;

import com.blog.Model.TableModel.UserInfo;
import com.blog.Model.UpdateProfileRequest;
import com.blog.Model.UpdateProfileResponse;
import com.blog.Utils.ResponseStatus;
import com.blog.repository.LoginRepository;
import com.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.ExportException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UpdateProfileService {
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    UserRepository userRepository;
    public UpdateProfileService(){

    }

    public UpdateProfileResponse updateProfile(UpdateProfileRequest request){

        UpdateProfileResponse response = new UpdateProfileResponse();
        UserInfo info = new UserInfo();

        info = userRepository.findByUsername(request.getUsername());
        String username = request.getUsername();
        String phoneNumber = request.getPhoneNumber();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String email = request.getEmail();
        String birthday = request.getBirthday();

        List<String> message = new ArrayList<>();
        List<String> success = new ArrayList<>();
        if(Objects.nonNull(phoneNumber)){
            try {
                userRepository.updatePhoneNumber(phoneNumber, username);
                success.add("Successfully update phone number");
            }catch (Exception e){
                message.add("Failure update phone number");
            }
        }
        if(Objects.nonNull(firstName)){
            try{
                userRepository.updateFirstName(firstName, username);
                success.add("Successfully update first name");
            }catch (Exception e){
                message.add("Failure update first name");
            }
        }
        if(Objects.nonNull(lastName)){
            try{
                userRepository.updateLastName(lastName, username);
                success.add("Successfully update last name");
            }catch(Exception e){
                message.add("Failure update last name");
            }
        }
        if(Objects.nonNull(email)){
            try{
                userRepository.updateEmail(email, username);
                success.add("Successfully update email");
            }catch(Exception e){
                message.add("Failure update email");
            }
        }
        if(Objects.nonNull(birthday)){
            try{
                userRepository.updateBirthday(birthday, username);
                success.add("Successfully update birthday");
            }catch(Exception e){
                message.add("Failure update birthday");
            }
        }

        if(message.size() == 0){
            response.setResponseStatus(ResponseStatus.Success);
            response.setResponseMessage("Successfully update Profile");
        }

        if(success.size() ==0){
            response.setResponseStatus(ResponseStatus.Failure);
            response.setResponseMessage("Profile failure to update");
        }

        if(message.size() != 0){
            StringBuilder sb = new StringBuilder();
            StringBuilder sbb = new StringBuilder();
            for(int i = 0; i < message.size(); i++){
                sb.append(message.get(i));
                sb.append("; ");
            }

            for(int i = 0; i < success.size(); i++){
                sbb.append(success.get(i));
                sbb.append("; ");
            }
            String failureMessage= sb.toString();
            String successMessage = sbb.toString();
            response.setResponseStatus(ResponseStatus.Partial);
            response.setResponseMessage(failureMessage + successMessage);
        }

        return response;
    }
}
