package com.blog.repository;

import com.blog.Model.TableModel.LoginInfo;
import com.blog.Model.TableModel.PostContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface LoginRepository extends JpaRepository<LoginInfo, Long> {

    LoginInfo findByUsername(String username);

    @Transactional
    @Modifying
    @Query("update LOGIN_INFO l set l.active = ?1 where l.username = ?2")
    public void setActiveStatusForLogin(boolean status, String username);

    @Transactional
    @Modifying
    @Query("update LOGIN_INFO l set l.password = ?1 where l.username = ?2")
    public void changePasswordForLogin(String password, String username);

}
