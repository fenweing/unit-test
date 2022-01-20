package com.tuanbaol.unittest;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public String getUserName(String id) {
        return "tom";
    }
}
