package com.tuanbaol.unittest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//        classes = UnitTestApplication.class)
//@AutoConfigureMockMvc
public class UnitTestApplicationTests {
    @Mock
//    @Spy
    private UserMapper userMapper;
    @InjectMocks
//    @Autowired
    private UserService userService;

    @Test
   public void get_userName() {
        when(userMapper.getUserName("1")).thenReturn("jim");
        assertEquals(userService.getUserName("1"), "jim_suffix");
    }

}
