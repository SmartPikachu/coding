package cn.jjx.coding.springboot.mockito;

import cn.jjx.coding.springboot.dao.OrmDepartmentDao;
import cn.jjx.coding.springboot.dao.OrmUserDao;
import cn.jjx.coding.springboot.model.OrmDepartmentPO;
import cn.jjx.coding.springboot.model.OrmUserPO;
import cn.jjx.coding.springboot.service.HrService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class HrServiceTest {
    @Mock
    private OrmDepartmentDao ormDepartmentDao;
    @Mock
    private OrmUserDao ormUserDao;
    @InjectMocks
    private HrService hrService;

    @DisplayName("根据部门名称，查询用户")
    @Test
    void findUserByDeptName() {
        Long deptId = 100L;
        String deptName = "行政部";
        OrmDepartmentPO ormDepartmentPO = new OrmDepartmentPO();
        ormDepartmentPO.setId(deptId);
        ormDepartmentPO.setDepartmentName(deptName);
        OrmUserPO user1 = new OrmUserPO();
        user1.setId(1L);
        user1.setUsername("001");
        user1.setDepartmentId(deptId);
        OrmUserPO user2 = new OrmUserPO();
        user2.setId(2L);
        user2.setUsername("002");
        user2.setDepartmentId(deptId);
        List<OrmUserPO> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        Mockito.when(ormDepartmentDao.findOneByDepartmentName(deptName))
                .thenReturn(
                        Optional.ofNullable(ormDepartmentPO)
                                .filter(dept -> deptName.equals(dept.getDepartmentName()))
                );
        Mockito.doReturn(
                userList.stream()
                        .filter(user -> deptId.equals(user.getDepartmentId()))
                        .collect(Collectors.toList())
        ).when(ormUserDao).findByDepartmentId(deptId);

        List<OrmUserPO> result1 = hrService.findUserByDeptName(deptName);
        List<OrmUserPO> result2 = hrService.findUserByDeptName(deptName + "error");

        Assertions.assertEquals(userList, result1);
        Assertions.assertEquals(Collections.emptyList(), result2);
    }
}