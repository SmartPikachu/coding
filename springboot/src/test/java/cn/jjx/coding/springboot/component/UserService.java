package cn.jjx.coding.springboot.component;

import cn.jjx.coding.springboot.dao.UserDao;
import cn.jjx.coding.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    public Integer insertUser(User user) {
        return userDao.insertUser(user);
    }

    public String getUserName(String someId) {
        return "TEST";
    }
}
