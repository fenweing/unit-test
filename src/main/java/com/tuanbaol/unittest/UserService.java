package com.tuanbaol.unittest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Value("${user.name.first}")
    private String username;
    @Autowired
    private UserMapper userMapper;

    public String getUserName(String id) {
        return userMapper.getUserName(id) + "_suffix";
    }

    public String getUserNameSuffix() {
        return "suffix";
    }

    public String getUserNameFromConfig() {
        return username;
    }
}
