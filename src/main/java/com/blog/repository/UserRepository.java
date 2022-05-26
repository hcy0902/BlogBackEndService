package com.blog.repository;

import com.blog.Model.TableModel.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findByUsername (String username);

    @Transactional
    @Modifying
    @Query("update USER_INFO u set u.active = ?1 where u.username = ?2")
    public void setActiveStatusForUser(boolean status, String username);

}
