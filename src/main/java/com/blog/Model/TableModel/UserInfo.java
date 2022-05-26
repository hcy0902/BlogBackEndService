package com.blog.Model.TableModel;

import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "USER_INFO")
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
    @Column(columnDefinition = "boolean default true")
    private boolean active;
}
