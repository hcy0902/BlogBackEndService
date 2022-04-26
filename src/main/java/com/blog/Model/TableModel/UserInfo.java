package com.blog.Model.TableModel;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class UserInfo {

    @Id
    @GeneratedValue
    private long id;

    private String lastName;
    private String firstName;
    private String birthday;
    private String phoneNumber;
    private String email;
    private String username;
}
