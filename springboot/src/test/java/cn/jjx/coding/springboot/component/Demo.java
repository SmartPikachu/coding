package cn.jjx.coding.springboot.component;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class Demo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Demo.class);
        GoodPerson bean = applicationContext.getBean(GoodPerson.class);
        System.out.println(bean);
    }

    class MyImportSelector implements ImportSelector {
        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            return new String[]{"cn.jjx.coding.springboot.component.Demo"};
        }
    }
}
