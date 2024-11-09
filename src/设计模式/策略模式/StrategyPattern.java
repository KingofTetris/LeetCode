package 设计模式.策略模式;

/**
 * @author by KingOfTetris
 * @date 2024/11/8
 */

//策略模式 就是把一类策略集合到一起，你需要什么策略，就指定什么策略即可
//比如线程池的拒绝策略，有四种算法，它们都实现了RejectionExecutionHandler

/**
 * 策略模式优点：
 *
 * 扩展性好，可以在不修改对象结构的情况下，为新的算法进行添加新的类进行实现；
 * 灵活性好，可以对算法进行自由切换；
 *
 * 策略模式缺点：
 *
 * 使用策略类变多，会增加系统的复杂度。；
 * 客户端必须知道所有的策略类才能进行调用；
 *
 * 使用场景：
 *
 * 如果在一个系统里面有许多类，它们之间的区别仅在于它们的行为，那么使用策略模式可以动态地让一个对象在许多行为中选择一种行为；
 * 一个系统需要动态地在几种算法中选择一种;
 * 如果一个对象有很多的行为，如果不用恰当的模式，这些行为就只好使用多重的条件选择语句来实现;
 */
public class StrategyPattern {
    public static void main(String[] args) {
        int a = 4, b = 2;
        //需要什么策略就传入什么策略。
        CalculatorContext context = new CalculatorContext(new OperationAdd());
        System.out.println("a + b = " + context.executeStrategy(a, b));

        CalculatorContext context2 = new CalculatorContext(new OperationSub());
        System.out.println("a - b = " + context2.executeStrategy(a, b));

        CalculatorContext context3 = new CalculatorContext(new OperationMul());
        System.out.println("a * b = " + context3.executeStrategy(a, b));

        CalculatorContext context4 = new CalculatorContext(new OperationDiv());
        System.out.println("a / b = " + context4.executeStrategy(a, b));
    }
}

/**
 * 这里为了方便理解，我们就拿刚学习Java的时候使用计算方法来说吧。
 * 在使用计算器进行计算的时候，会经常用到加减乘除方法。如果我们想得到两个数字相加的和，我们需要用到“+”符号，得到相减的差，需要用到“-”符号等等。虽然我们可以通过字符串比较使用if/else写成通用方法，但是计算的符号每次增加，我们就不得不加在原先的方法中进行增加相应的代码，如果后续计算方法增加、修改或删除，那么会使后续的维护变得困难。
 * 但是在这些方法中，我们发现其基本方法是固定的，这时我们就可以通过策略模式来进行开发，可以有效避免通过if/else来进行判断，即使后续增加其他的计算规则也可灵活进行调整。
 * <p>
 * 首先定义一个抽象策略角色，并拥有一个计算的方法。
 */

//环境角色
class CalculatorContext {
    private CalculateStrategy strategy;

    public CalculatorContext(CalculateStrategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}

interface CalculateStrategy {
    int doOperation(int num1, int num2);
}

class OperationAdd implements CalculateStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}

class OperationSub implements CalculateStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

class OperationMul implements CalculateStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}

class OperationDiv implements CalculateStrategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 / num2;
    }
}
