package com.tuanbaol.unittest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.SpringBootDependencyInjectionTestExecutionListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
//@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
//        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = UnitTestApplication.class)
//@AutoConfigureMockMvc
@TestExecutionListeners({MockitoTestExecutionListener.class
        , SpringBootDependencyInjectionTestExecutionListener.class
        , MockitoBeansTestExecutionListener.class})
//@ContextConfiguration(classes = {MockObjectInjectionTests.class})
public class MockObjectInjectionTests {
    @Mock
    private UserMapper userMapper;
    @Autowired
//    @InjectMocks
    private UserService userService;

    @Test
    public void get_userName() {
        when(userMapper.getUserName("1")).thenReturn("jim");
        assertEquals(userService.getUserName("1"), "jim_suffix");
    }
    @Test
    public void get_userName_from_config() {
        assertEquals(userService.getUserNameFromConfig(), "tom");
    }

}
