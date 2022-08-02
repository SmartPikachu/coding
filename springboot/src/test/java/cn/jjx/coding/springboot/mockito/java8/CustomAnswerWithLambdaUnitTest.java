package cn.jjx.coding.springboot.mockito.java8;

import cn.jjx.coding.springboot.mockito.java8.vo.JobPosition;
import cn.jjx.coding.springboot.mockito.java8.vo.Person;
import org.junit.Before;
import org.mockito.Mock;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CustomAnswerWithLambdaUnitTest {

    @Mock
    private JobService jobService;

    @Before
    public void init() {
        when(jobService.listJobs(any(Person.class))).then((i) ->
                Stream.of(new JobPosition("Teacher"))
                        .filter(p -> ((Person) i.getArgument(0)).getName().equals("Peter")));
    }
}