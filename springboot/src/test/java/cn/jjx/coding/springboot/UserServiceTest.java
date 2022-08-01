package cn.jjx.coding.springboot;

import cn.jjx.coding.springboot.component.UserService;
import cn.jjx.coding.springboot.dao.UserDao;
import cn.jjx.coding.springboot.model.User;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserServiceTest {

@Autowired
private UserService userService;

@MockBean
private UserDao userDao;

@Test
public void getUserById() throws Exception {
// 定义当调用mock userDao的getUserById()方法，并且参数为3时，就返回id为200、name为I'm mock3的user对象
        Mockito.when(userDao.getUserById(3)).thenReturn(new User(200, "I'm mock 3"));

// 返回的会是名字为I'm mock 3的user对象
        User user = userService.getUserById(3);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getId(), new Integer(200));
        Assertions.assertEquals(user.getName(), "I'm mock 3");
     }
}
