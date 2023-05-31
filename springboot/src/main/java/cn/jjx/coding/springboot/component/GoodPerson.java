package cn.jjx.coding.springboot.component;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GoodPerson {
    private String age;
    private String address;
    private String name;
}
