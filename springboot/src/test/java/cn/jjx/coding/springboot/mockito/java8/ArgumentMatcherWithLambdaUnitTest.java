package cn.jjx.coding.springboot.mockito.java8;

import cn.jjx.coding.springboot.mockito.java8.vo.JobPosition;
import cn.jjx.coding.springboot.mockito.java8.vo.Person;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class ArgumentMatcherWithLambdaUnitTest {

    @Mock
    private JobService jobService;

    @InjectMocks
    private UnemploymentServiceImpl unemploymentService;


    @Test
    public void whenPersonWithJob_thenIsNotEntitled() {
        Person peter = new Person("Peter");
        Person linda = new Person("Linda");

        JobPosition teacher = new JobPosition("Teacher");

        when(jobService.findCurrentJobPosition(
                ArgumentMatchers.argThat(p -> p.getName().equals("Peter"))))
                .thenReturn(Optional.of(teacher));

        assertTrue(unemploymentService.personIsEntitledToUnemploymentSupport(linda));
        assertFalse(unemploymentService.personIsEntitledToUnemploymentSupport(peter));
    }
}
