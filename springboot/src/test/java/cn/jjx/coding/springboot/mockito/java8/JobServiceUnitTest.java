package cn.jjx.coding.springboot.mockito.java8;





import cn.jjx.coding.springboot.mockito.java8.vo.JobPosition;
import cn.jjx.coding.springboot.mockito.java8.vo.Person;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

public class JobServiceUnitTest {

    @Mock
    private JobService jobService;

    @Test
    public void givenDefaultMethod_whenCallRealMethod_thenNoExceptionIsRaised() {
        Person person = new Person();

        when(jobService.findCurrentJobPosition(person))
                .thenReturn(Optional.of(new JobPosition()));

        doCallRealMethod().when(jobService)
                .assignJobPosition(
                        Mockito.any(Person.class),
                        Mockito.any(JobPosition.class)
                );

        assertFalse(jobService.assignJobPosition(person, new JobPosition()));
    }
}
