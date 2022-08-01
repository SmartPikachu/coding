package cn.jjx.coding.springboot.dao;

import cn.jjx.coding.springboot.model.OrmDepartmentPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Mapper
public interface OrmDepartmentDao {

    Optional<OrmDepartmentPO> findOneByDepartmentName(String departmentName);

}
