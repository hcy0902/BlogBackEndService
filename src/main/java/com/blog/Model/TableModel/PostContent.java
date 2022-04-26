package com.blog.Model.TableModel;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class PostContent {

    public PostContent(){

    }

    @Id
    @GeneratedValue
    long contentId;

    String content;
    String createdByUser;
    String username;
}
