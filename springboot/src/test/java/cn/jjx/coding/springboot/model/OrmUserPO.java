package cn.jjx.coding.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrmUserPO {
    private long id;
    private long departmentId;
    private String username;
}
