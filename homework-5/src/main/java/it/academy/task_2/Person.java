package it.academy.task_2;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

@Getter
@Setter
public class Person implements InitializingBean, DisposableBean {
    private String name;
    private String surname;

    public static Person getInstance() {
        return new Person();
    }

    public void defaultInit() {
        System.out.println("Call defaultInit() " + name + " " + surname);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Call afterPropertiesSet() " + name + " " + surname);
    }

    public void defaultDestroy() {
        System.out.println("Call defaultDestroy() " + name + " " + surname);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Call destroy() " + name + " " + surname);
    }
}
