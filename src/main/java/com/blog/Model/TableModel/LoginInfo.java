package com.blog.Model.TableModel;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class LoginInfo {

    @Id
    @GeneratedValue
    long id;

    private String username;
    private String password;
    private String email;


}
