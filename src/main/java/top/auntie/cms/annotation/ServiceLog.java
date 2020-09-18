package top.auntie.cms.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
public @interface ServiceLog {

    String value();

    boolean isOutter() default false;

}
