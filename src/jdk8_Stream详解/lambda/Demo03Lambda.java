package jdk8_Stream详解.lambda;


import jdk8_Stream详解.lambda.service.UserService;

public class Demo03Lambda {

    public static void main(String[] args) {
        /*goShow(new UserService() {
            @Override
            public void show() {
                System.out.println("show 方法执行了...");
            }
        });
        System.out.println("----------");*/
        goShow(() -> {
            System.out.println("Lambda show 方法执行了...");
        });
    }

    public static void goShow(UserService userService){
        userService.show();
    }


}
