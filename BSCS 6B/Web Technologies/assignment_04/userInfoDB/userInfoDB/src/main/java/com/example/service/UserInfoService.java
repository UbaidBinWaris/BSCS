package com.example.service;

import com.example.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
}
