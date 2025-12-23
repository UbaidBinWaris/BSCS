package com.example.postrequest.repository;

import com.example.postrequest.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    List<UserInfo> findAll();

    UserInfo findUserInfoByName(String name);
}
