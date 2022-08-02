package cn.jjx.coding.springboot.mockito.java8;

import cn.jjx.coding.springboot.mockito.java8.vo.JobPosition;
import cn.jjx.coding.springboot.mockito.java8.vo.Person;

import java.util.Optional;

public interface UnemploymentService {
    boolean personIsEntitledToUnemploymentSupport(Person person);
    Optional<JobPosition> searchJob(Person person, String searchString);
}
