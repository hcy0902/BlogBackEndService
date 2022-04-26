package com.blog.repository;

import com.blog.Model.TableModel.PostContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostContent, Long> {

    List<PostContent> findByUsername(String username);

}
