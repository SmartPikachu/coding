package cn.jjx.coding.springboot.mockito.java8;

import cn.jjx.coding.springboot.mockito.java8.vo.JobPosition;
import cn.jjx.coding.springboot.mockito.java8.vo.Person;



import java.util.Optional;
import java.util.stream.Stream;

public class JobServiceImpl implements JobService{

    @Override
    public Optional<JobPosition> findCurrentJobPosition(Person person) {
        return Optional.of(new JobPosition());
    }

    @Override
    public Stream<JobPosition> listJobs(Person person) {
        return Stream.of(new JobPosition());
    }


}
