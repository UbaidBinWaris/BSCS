//package com.example.service;
//
//import com.example.repository.UserInfoRepository;
//import lombok.RequiredArgsConstructor;
//
//@RequiredArgsConstructor
//public class UserInfoService {
//
//    private final UserInfoRepository userInfoRepository;
//}


package com.example.service;

import com.example.model.UserInfo;
import com.example.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    // Constructor injection (manual)
    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    // Method to save user info to database
    public UserInfo saveUserInfo(UserInfo userInfo) {
        return userInfoRepository.save(userInfo);
    }

    // Method to get all users from database
    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }
}
