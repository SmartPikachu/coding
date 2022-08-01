package cn.jjx.coding.springboot.mockito;

import cn.jjx.coding.springboot.dao.OrmDepartmentDao;
import cn.jjx.coding.springboot.model.OrmDepartmentPO;
import cn.jjx.coding.springboot.model.OrmUserPO;
import cn.jjx.coding.springboot.service.HrService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class HrServiceTest2 {
    @Autowired
    private HrService hrService;
    @SpyBean
    private OrmDepartmentDao ormDepartmentDao;

    @DisplayName("根据部门名称，查询用户")
    @Test
    void findUserByDeptName() {
        String deptName="行政部";
        OrmDepartmentPO ormDepartmentPO = new OrmDepartmentPO();
        ormDepartmentPO.setDepartmentName(deptName);
        Mockito.when(ormDepartmentDao.findOneByDepartmentName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(ormDepartmentPO));
        List<OrmUserPO> userList = hrService.findUserByDeptName(deptName);
        Assertions.assertTrue(userList.size() > 0);
    }
}
