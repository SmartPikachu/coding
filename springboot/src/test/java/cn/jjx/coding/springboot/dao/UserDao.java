package cn.jjx.coding.springboot.dao;

import cn.jjx.coding.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User getUserById(Integer id);
    Integer insertUser(User user);
}
