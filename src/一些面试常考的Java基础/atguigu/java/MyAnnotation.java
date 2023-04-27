package 一些面试常考的Java基础.atguigu.java;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author KingofTetris
 * @File MyAnnotation
 * @Time 2021/10/20  20:02
 */

@Target({ElementType.TYPE,ElementType.METHOD,ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String[] value() default "hello";
}
