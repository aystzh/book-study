package java8;

import lombok.Data;

/**
 * created by zhanghuan on 2020/4/6.
 */
@Data
public class Human {
    private String name;

    private int age;

    public Human(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }
}
