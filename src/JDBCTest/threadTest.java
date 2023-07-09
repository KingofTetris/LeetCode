package JDBCTest;

/**
 * @author by KingOfTetris
 * @date 2022/9/5
 * 两个线程交替打印，字母在前，数字在后
 */
public class threadTest {
    public static void main(String[] args) {
       /* MyThread3 myThread = new MyThread3();
        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);
        Thread t3 = new Thread(myThread);

        t1.setName("线程1");
        t2.setName("线程2");
        t3.setName("线程3");

        t1.start();
        t2.start();
        t3.start();*/
        Object obj = new Object();

        Thread t1 = new Thread1(obj);
        Thread t2 = new Thread2(obj);

        t1.setName("线程1");
        t2.setName("线程2");

        //如果你没用设置join 那么start的顺序就有所谓了。
        t2.start();
        t1.start();
    }

}
/**
 * 实现两个进程交替打印1-100
 */
class MyThread3 implements Runnable{
    private int num = 1;
    @Override
    public void run() {
        while (true){
            synchronized (this){
                notifyAll();
                if (num <= 100){
                    System.out.println(Thread.currentThread().getName() + "打印数字：" + num);
                    num++;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }
    }
}

/**
 * 优先采用Runnable接口
 */
class MyThread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int num = (int) (Math.random() * 101);
            System.out.println(Thread.currentThread().getName() + "生成数字:" + num);
        }
    }
}

/**
 * 优先采用Runnable接口
 */
class MyThread2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int num = (int) (Math.random() * 101);
            System.out.println(Thread.currentThread().getName() + "打印数字:" + num);
        }
    }
}


/**
 * 交替打印字母数字
 */
class Myprint{
    int count=0;//记录打印的轮数（也是初始数字1的前一个数字）；
    char c=64;//初始字母‘A’的前一个位置；
    boolean flag=true;//用来判断该睡打印的布尔值，每个线程打印完反转，让另一个线打印；
    //打印数字的方法
    public  synchronized void printNum()  {
        if (flag==false){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(++count);
        flag=false;
        notify();
    }
    //打印字母的方法
    public  synchronized void pringChar() {
        if (flag==true){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //小心char类型参与运算会转换成int参与，结果为int,需要强转成char;
        System.out.print((char)(c+count));
        flag=true;
        notify();
    }
}


class Thread1 extends Thread
{   //第一个线程extend
    private Object obj;

    public Thread1(Object obj)
    {
        this.obj = obj;
    }

    public void run()
    {
        // 加锁
        synchronized (obj)
        {
            // 打印1-26
            for (int i = 1; i <= 26; i++)
            {
                System.out.print(i);
                obj.notifyAll();//唤醒其他所有处于waiting的进程
                try
                {
                    obj.wait(); //释放锁
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Thread2 extends Thread {
    private Object obj;//仍然是private

    public Thread2(Object obj)
    {
        this.obj = obj;
    }

    public void run()
    {
        synchronized (obj) //加锁
        {
            // 打印A-Z
            for (int i = 0; i <26;i++)
            {
                System.out.print((char) ('a' + i));
                // 唤醒其它线程
                obj.notifyAll();
                try
                {
                    obj.wait();//释放所
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
