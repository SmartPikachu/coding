package cn.jjx.coding.springboot.mockito.java8;

import cn.jjx.coding.springboot.mockito.java8.vo.JobPosition;
import cn.jjx.coding.springboot.mockito.java8.vo.Person;

import java.util.Optional;

public class UnemploymentServiceImpl implements UnemploymentService {

    private JobService jobService;

    public UnemploymentServiceImpl(JobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public boolean personIsEntitledToUnemploymentSupport(Person person) {
        Optional<JobPosition> optional = jobService.findCurrentJobPosition(person);
        return !optional.isPresent();
    }

    @Override
    public Optional<JobPosition> searchJob(Person person, String searchString) {
        return jobService.listJobs(person)
                .filter((j) -> j.getTitle().contains(searchString))
                .findFirst();
    }
}
