package 设计模式.状态模式;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */

/**
 * 状态模式，其主要的的思想就是提供一种状态，提供给客户端进行调用。状态可谓无处不在，
 * 无论是电脑、手机等电子产品的开机和关机的状态，
 * 还是经常用到的网络在线和离线状态，即使是在我们编程中Tcp也有创建、监听、关闭状态。
 *
 */


/**
 * 状态模式优点：
 *
 * 扩展性好，将和状态有关的行为放到一起，增加新的的状态，只需要改变对象状态即可改变对象的行为即可；
 * 复用性好，让多个环境对象共享一个状态对象，从而减少系统中对象的个数；
 *
 * 状态模式缺点：
 *
 * 使用状态模式会增加系统类和对象的个数，并且该模式的结构与实现都较为复杂，如果使用不当将导致程序结构和代码的混乱；
 * 状态模式对"开闭原则"的支持并不太好，对于可以切换状态的状态模式，增加新的状态类需要修改那些负责状态转换的源代码，否则无法切换到新增状态，而且修改某个状态类的行为也需修改对应类的源代码。
 *
 * 使用场景：
 *
 * 行为随状态改变而改变的场景；
 * 条件、分支语句的代替者。
 *
 * 注意事项 ：
 *
 * 在行为受状态约束的时候使用状态模式，而且状态不超过5个。
 **/

 /* ** 和策略模式比较：**
 * 在学习状态模式的时候，很容易和策略模式搞混，因为它们实在是太像了，
 * 很难区分，在查阅一番资料之后，整理了如下的相同点和区别点。
 *
 * 相同点:
 *
 * 它们很容易添加新的状态或策略，而且不需要修改使用它们的Context对象。
 * 它们都符合OCP原则，在状态模式和策略模式中，Context对象对修改是关闭的，添加新的状态或策略，都不需要修改Context。
 * 它们都会初始化。
 * 它们都依赖子类去实现相关行为。
 * 区别点
 *
 * 状态模式的行为是平行性的，不可相互替换的；
 * 而策略模式的行为是平等性的，是可以相互替换的。
 * 最重要的一个不同之处是，策略模式的改变由客户端完成；
 * 而状态模式的改变，由环境角色或状态自己.
 */
public class StatePattern {

    public static void main(String[] args) {
        Headset hs = new Headset(new PlayState());
        //第一次播放音乐
        hs.press();
        //第二次暂停音乐
        hs.press();
        //第三次播放音乐
        hs.press();
    }
}
interface MusicState{
    void press();
}
class PlayState implements MusicState{

    @Override
    public void press() {
        System.out.println("播放音乐!");
    }
}

class PauseState implements MusicState{

    @Override
    public void press() {
        System.out.println("暂停音乐!");
    }
}

class Headset{
    private MusicState state;
    private int i;
    public Headset(MusicState state){
        this.state=state;
    }
    public void press() {
        if((i&1)==0) {
            this.state=new PlayState();
        }else {
            this.state=new PauseState();
        }
        this.state.press();
        i++;
    }
    public MusicState getState() {
        return state;
    }
    public void setState(MusicState state) {
        this.state = state;
    }

}
