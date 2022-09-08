package enumTest;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File enumTest
 * @Time 2021/10/30  15:06
 *
 * enum的三个常用方法
 * values() toString() valuesOf(String s)
 *
 * enum类也可以实现接口
 */
public class enumTest {

    @Test
    public void test(){
        Season summer  = Season.SUMMER;

        //toString
        System.out.println(summer);
        System.out.println(summer.toString());
        System.out.println(Season.class.getSuperclass());//class java.lang.Enum 并不是Object


        //values(); 查看enum类里面有多少个常量
        Season[] values = Season.values();
        for(Season s:values){
            System.out.println(s);
        }


        //根据名字返回枚举类中的同名对象
        //注意区分大小写 找不到会运行时报错 No enum constant enumTest.Season.winter
        Season winter = Season.valueOf("WINTER");
        System.out.println(winter);
        winter.show();
        summer.show();
    }



}


interface info{
    void show();
}
//默认继承java.lang.Enum
enum Season implements info{

    //提供当前枚举类的对象，多个对象间用，隔开 结尾用;
    //都是用public static final来修饰的，和接口一样省略了
    SPRING("春天","春暖花开"){
        @Override
        public void show() {
            System.out.println("春天在哪里");
        }
    },
    SUMMER("夏天","夏日炎炎"){
        @Override
        public void show() {
            System.out.println("宁夏");
        }
    },
    FALL("秋天","秋高气爽"){
        @Override
        public void show() {
            System.out.println("秋天不回来");
        }
    },
    WINTER("冬天","冰天雪地"){
        @Override
        public void show() {
            System.out.println("大约在冬季");
        }
    };


    String seansonName;
    String seansonDesc;

    //可以看见本来就是private
    private Season(String seansonName,String seansonDesc){
        this.seansonDesc = seansonDesc;
        this.seansonName = seansonName;
    }

//    @Override
//    public void show() {
//        System.out.println("四季");
//    }

    //toString默认打印对象常量的名字
}
