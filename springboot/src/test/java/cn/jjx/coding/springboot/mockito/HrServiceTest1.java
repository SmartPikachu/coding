package cn.jjx.coding.springboot.mockito;

import cn.jjx.coding.springboot.model.OrmUserPO;
import cn.jjx.coding.springboot.service.HrService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class HrServiceTest1 {
    @Autowired
    private HrService hrService;

    @DisplayName("根据部门名称，查询用户")
    @Test
    void findUserByDeptName() {
        List<OrmUserPO> userList = hrService.findUserByDeptName("行政部");
        Assertions.assertTrue(userList.size() > 0);
    }
}
