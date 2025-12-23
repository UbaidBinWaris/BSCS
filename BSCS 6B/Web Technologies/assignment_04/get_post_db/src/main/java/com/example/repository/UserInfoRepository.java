package com.example.repository;

import com.example.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {
    List<UserInfo> findAll();

    UserInfo findUserInfoByName(String name);
}
