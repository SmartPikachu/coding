package cn.jjx.coding.springboot.dao;

import cn.jjx.coding.springboot.model.OrmUserPO;

import java.util.List;
import java.util.Optional;

public interface OrmUserDao {
    List<OrmUserPO> findByDepartmentId(Long id);
}
