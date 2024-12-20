package 设计模式.代理模式;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */

/**
 * 代理模式主要由这三个角色组成，抽象角色、代理角色和真实角色。
 *
 * 抽象角色：通过接口或抽象类声明真实角色实现的业务方法。
 * 代理角色：实现抽象角色，是真实角色的代理，通过真实角色的业务逻辑方法来实现抽象方法，并可以附加自己的操作。
 * 真实角色：实现抽象角色，定义真实角色所要实现的业务逻辑，供代理角色调用。
 *
 *
 * 代理模式又分为静态代理、动态代理。
 *
 * 静态代理是由程序员创建或工具生成代理类的源码，再编译代理类。
 * 所谓静态也就是在程序运行前就已经存在代理类的字节码文件，代理类和委托类的关系在运行前就确定了。
 * 动态代理是在实现阶段不用关心代理类，而在运行阶段才指定哪一个对象。
 *
 */
public class ProxyPattern {

}
