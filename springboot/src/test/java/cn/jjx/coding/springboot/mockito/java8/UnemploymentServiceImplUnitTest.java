package cn.jjx.coding.springboot.mockito.java8;

import cn.jjx.coding.springboot.mockito.java8.vo.Person;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


public class UnemploymentServiceImplUnitTest {

    @Mock
    private JobService jobService;

    @InjectMocks
    private UnemploymentServiceImpl unemploymentService;

    @Test
    public void givenReturnIsOfTypeOptional_whenMocked_thenValueIsEmpty() {
        Person person = new Person();
        when(jobService.findCurrentJobPosition(any(Person.class)))
                .thenReturn(Optional.empty());
        assertTrue(unemploymentService.personIsEntitledToUnemploymentSupport(person));
    }

    @Test
    public void givenReturnIsOfTypeStream_whenMocked_thenValueIsEmpty() {
        Person person = new Person();
        when(jobService.listJobs(any(Person.class))).thenReturn(Stream.empty());
        assertFalse(unemploymentService.searchJob(person, "").isPresent());
    }

    @Test
    public void givenReturnIsStream_whenDefaultValueIsReturned_thenValueIsEmpty() {
        Person person = new Person();
        assertFalse(unemploymentService.searchJob(person, "").isPresent());
    }

}