package cn.jjx.coding.springboot.service;


import cn.jjx.coding.springboot.dao.OrmDepartmentDao;
import cn.jjx.coding.springboot.dao.OrmUserDao;
import cn.jjx.coding.springboot.model.OrmDepartmentPO;
import cn.jjx.coding.springboot.model.OrmUserPO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class HrService {
    private final OrmDepartmentDao ormDepartmentDao;
    private final OrmUserDao ormUserDao;

    public List<OrmUserPO> findUserByDeptName(String deptName) {
        return ormDepartmentDao.findOneByDepartmentName(deptName)
                .map(OrmDepartmentPO::getId)
                .map(ormUserDao::findByDepartmentId)
                .orElse(Collections.emptyList());
    }
}
