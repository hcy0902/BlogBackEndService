package com.blog.repository;

import com.blog.Model.TableModel.LoginInfo;
import com.blog.Model.TableModel.PostContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginInfo, Long> {

    LoginInfo findByUsername(String username);

}
