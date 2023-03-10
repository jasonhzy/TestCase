package cn.jasonhu.commons.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Student {

    private Integer id;

    private String username;

    private Integer age;
}
