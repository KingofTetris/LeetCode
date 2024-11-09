package 设计模式.外观模式;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */

//外观模式隐藏系统的复杂性，并向客户端提供了一个客户端可以访问系统的接口。
// 这种类型的设计模式属于结构型模式，它向现有的系统添加一个接口，来隐藏系统的复杂性。

/**
 * 简单的来说就是对外提供一个简单接口，隐藏实现的逻辑。比如常用电脑的电源键，我们只需按电源键，就可以让它启动或者关闭，无需知道它是怎么启动的(启动CPU、启动内存、启动硬盘)，怎么关闭的(关闭硬盘、关闭内存、关闭CPU)；
 * <p>
 * 这里我们还是可以用电脑玩游戏的例子来外观模式进行简单的讲解。
 * 电脑上有一些网络游戏，分别是DNF、LOL和WOW，我们只需双击电脑上的图标就可以启动并玩游戏了，无需关心游戏是怎么启动和运行的了。
 * <p>
 * 需要实现的步骤如下:
 * <p>
 * 建立游戏的接口；
 * 建立LOL、DNF和WOW的类并实现游戏的接口；
 * 定义一个外观类，提供给客户端调用。
 * 调用外观类。
 */
public class FacadePattern {
    /**
     * 外观模式的优点:
     *
     * 降低了耦合，从某种方面来说也提升了安全性。
     *
     * 外观模式的缺点:
     *
     * 不符合开闭原则，不易更改。
     *
     * 只用实例化外观类调用其中的游戏方法即可，无需关心游戏是怎么启动和运行的。
     * 而且每个游戏之间也相互独立，互不影响，不会因为某个游戏玩不了导致其它的游戏也无法运行。
     * 其实感觉外观模式和我们平时使用接口很相像，都是对外提供接口，并不需要关心是如何实现的。
     * @param args
     */
    public static void main(String[] args) {
        Computer computer = new Computer();
        //给你一个门户，具体怎么实现的不需要你关心。
        computer.playDNF();
        computer.playLOL();
        computer.playWOW();
    }
}

interface Game {
    void play();
}

class DNF implements Game {

    @Override
    public void play() {
        System.out.println("正在玩DNF...");
    }
}

class LOL implements Game {
    @Override
    public void play() {
        System.out.println("正在玩LOL...");
    }
}

class WOW implements Game {
    @Override
    public void play() {
        System.out.println("正在玩WOW...");
    }
}

class Computer {

    private Game dnf;
    private Game lol;
    private Game wow;

    public Computer() {
        dnf = new DNF();
        lol = new LOL();
        wow = new WOW();
    }

    public void playDNF() {
        dnf.play();
    }

    public void playLOL() {
        lol.play();
    }

    public void playWOW() {
        wow.play();
    }
}
