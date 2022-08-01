package cn.jjx.coding.springboot.junit5;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Objects;

import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("前置条件测试类")
public class AssumptionsTest {
    private final String environment = "TEST";

    @Test
    @DisplayName("true or false")
    public void simpleAssume() {
        assumeTrue(Objects.equals(this.environment, "TEST"));
        assumeFalse(() -> Objects.equals(this.environment, "PROD"));
    }

    @Test
    @DisplayName("assumingthat ")
    public void assumeThenDo() {
        assumingThat(
                Objects.equals(this.environment, "TEST"),
                () -> System.out.println("In TEST")
        );
    }
}
