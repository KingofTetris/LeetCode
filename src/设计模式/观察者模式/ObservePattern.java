package 设计模式.观察者模式;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author by KingOfTetris
 * @date 2024/11/8
 */

//其实就是订阅-发布模式
//谁订阅了我，我就发消息给谁
public class ObservePattern {

    public static void main(String[] args) {
        DeadBeat zhangsan = new DeadBeat("张三");
        DebtCollector lisi = new DebtCollector("李四", zhangsan, 10);
        DebtCollector wangwu = new DebtCollector("王五", zhangsan, 20);
        zhangsan.borrow(lisi);
        zhangsan.borrow(wangwu);
        while (zhangsan.money <= 40) {
            zhangsan.earnMoney();
        }
        //钱够了通知债主来取钱
        zhangsan.notifyCredits();
    }
}

//谁借了我钱，我就还给谁
interface Debit {
    //像贷款人借钱
    void borrow(Credit credit);

    //通知贷款人，我来还钱了
    void notifyCredits();
}

interface Credit {
    void takeMoney();
}

class DeadBeat implements Debit {
    public String name;
    private LinkedList<Credit> allCredit = new LinkedList<>();
    public Integer money = 0; //0 没钱，>= 0 有钱
    Random random = new Random();

    public DeadBeat(String name) {
        this.name = name;
    }

    @Override
    public void borrow(Credit credit) {
        allCredit.add(credit);//添加订阅者
    }

    @Override
    public void notifyCredits() {
        //通知，一旦我有钱了，就还钱
        //通知每个贷款方来取钱。
        allCredit.forEach(credit -> credit.takeMoney());
    }

    public void earnMoney() {
        money += random.nextInt(1, 5);
        System.out.println("张三正在打工卖血赚钱,目前资产" + money);
    }
}

class DebtCollector implements Credit {

    public String name;

    private int ownedMoney;//需要多少钱
    private DeadBeat db;//谁欠了他钱。

    public DebtCollector(String name, DeadBeat db, int ownedMoney) {
        this.ownedMoney = ownedMoney;
        this.name = name;
        this.db = db;
    }

    @Override
    public void takeMoney() {
        if (db.money >= ownedMoney) {
            db.money = db.money - ownedMoney;
            System.out.println(db.name + "还" + this.name + ownedMoney + "元");
        }
    }
}

