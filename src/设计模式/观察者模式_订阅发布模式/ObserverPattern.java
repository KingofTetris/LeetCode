package 设计模式.观察者模式_订阅发布模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */

/**
 * 观察者模式优点:
 *
 * 解除耦合，让耦合的双方都依赖于抽象，从而使得各自的变换都不会影响另一边的变换。
 *
 * 观察者模式缺点
 *
 * 如果一个被观察者对象有很多的直接和间接的观察者的话，将所有的观察者都通知到会花费很多时间；
 * 如果在观察者和观察目标之间有循环依赖的话，观察目标会触发它们之间进行循环调用，可能导致系统崩溃；
 * 观察者模式没有相应的机制让观察者知道所观察的目标对象是怎么发生变化的，而仅仅只是知道观察目标发生了变化。
 *
 * 使用场景：
 *
 * 需要关联行为的场景；
 * 事件需要创建一个触发链的场景，比如监控；
 * 跨系统的消息交换场景，比如消息队列、事件总线的处理机制。
 *
 * 注意事项：
 *
 * 如果顺序执行，某一观察者错误会导致系统卡壳，建议采用异步方式。
 */
public class ObserverPattern {

    public static void main(String[] args) {
        String name1 ="张三";
        String name2 ="李四";
        String	bingguo = "海贼王";
        String	fate = "火影忍者";
        BangumiSubject bs1 = new Bangumi(bingguo);
        BangumiSubject bs2 = new Bangumi(fate);

        UserObserver uo1 = new User(name1);
        UserObserver uo2 = new User(name2);

        //进行订阅
        bs1.toThem(uo1);
        bs1.toThem(uo2);
        bs2.toThem(uo1);
        bs2.toThem(uo2);
        //进行通知
        bs1.notifyUser();
        bs2.notifyUser();

        //取消订阅
        bs1.callOff(uo1);
        bs2.callOff(uo2);
        //进行通知
        bs1.notifyUser();
        bs2.notifyUser();
    }
}

interface BangumiSubject{

    void toThem(UserObserver user);

    void callOff(UserObserver user);

    void notifyUser();
}

interface UserObserver{

    void update(String bangumi);

    String getName();
}
class  Bangumi implements BangumiSubject {

    private List<UserObserver> list;
    private String  anime;
    public Bangumi(String anime) {
        this.anime = anime;
        list = new ArrayList<UserObserver>();
    }

    @Override
    public void toThem(UserObserver user) {
        System.out.println("用户"+user.getName()+"订阅了"+anime+"!");
        list.add(user);
    }

    @Override
    public void callOff(UserObserver user) {
        if(!list.isEmpty())
            System.out.println("用户"+user.getName()+"取消订阅"+anime+"!");
        list.remove(user);
    }

    @Override
    public void notifyUser() {
        System.out.println(anime+"更新了！开始通知订阅该番剧的用户！");
        list.forEach(user->
                user.update(anime)
        );
    }
}

class  User implements UserObserver{
    private String name;
    public User(String name){
        this.name = name;
    }

    @Override
    public void update(String bangumi) {
        System.out.println(name+"订阅的番剧: " + bangumi+"更新啦！");
    }

    @Override
    public String getName() {
        return name;
    }

}
