package jdk8_Stream详解.lambda;

import jdk8_Stream详解.lambda.service.OrderService;
import jdk8_Stream详解.lambda.service.StudentService;

public class Demo05Lambda {

    public static void main(String[] args) {
        goStudent((String name,Integer age)->{
            return name+age+" 6666 ...";
        });
        // 省略写法
        goStudent((name,age)-> name+age+" 6666 ..."
        );
        System.out.println("------");
        goOrder((String name)->{
            System.out.println("--->" + name);
            return 666;
        });
        // 省略写法
        goOrder(name -> {
            System.out.println("--->" + name);
            return 666;
        });
        goOrder(name ->  666);
    }

    public static void goStudent(StudentService studentService){
        studentService.show("张三",22);
    }

    public static void goOrder(OrderService orderService){
        orderService.show("李四");
    }

}
