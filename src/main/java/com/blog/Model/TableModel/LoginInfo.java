package com.blog.Model.TableModel;

import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "LOGIN_INFO")
public class LoginInfo {

    @Id
    @GeneratedValue
    long id;

    private String username;
    private String password;
    private String email;
    @Column(columnDefinition = "boolean default true")
    private boolean active;


}
