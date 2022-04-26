package com.blog.repository;

import com.blog.Model.TableModel.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

    UserInfo findByUsername (String username);

}
