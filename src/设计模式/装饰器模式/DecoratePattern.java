package 设计模式.装饰器模式;

/**
 * @author by KingOfTetris
 * @date 2024/11/8
 */
public class DecoratePattern {

    public static void main(String[] args) {
        //传入一个原始对象，对原始对象的方法进行增强。
        //这就是装饰器模式。
        //JavaIO流里面有大量的装饰器模式的应用，见图。
        //例如new BufferedInputStream(new InputStream()) 包装流
        new RobotDecorator(new FirstVersionRobot()).doMoreThing();
    }
}

interface Robot{
    void doSomething();
}

class FirstVersionRobot implements Robot{
    //原有功能
    @Override
    public void doSomething() {
        System.out.println("唱歌");
        System.out.println("跳舞");
    }
}


class RobotDecorator implements Robot{
    private Robot robot;
    //传入一个上层的robot
    public RobotDecorator(Robot robot){
        this.robot = robot;
    }

    //保持原有功能不变
    @Override
    public void doSomething() {
        robot.doSomething();
    }

    //不改变原有结构下，扩展功能
    public void doMoreThing(){
        System.out.println("洗衣");
        System.out.println("做饭");
    }
}
