package cn.jjx.coding.springboot.mockito.java8;

import cn.jjx.coding.springboot.mockito.java8.vo.JobPosition;
import cn.jjx.coding.springboot.mockito.java8.vo.Person;

import java.util.Optional;
import java.util.stream.Stream;

public interface JobService {

    Optional<JobPosition> findCurrentJobPosition(Person person);

    Stream<JobPosition> listJobs(Person person);

    default boolean assignJobPosition(Person person, JobPosition jobPosition) {
        if(!findCurrentJobPosition(person).isPresent()) {
            person.setCurrentJobPosition(jobPosition);

            return true;
        } else {
            return false;
        }
    }
}
