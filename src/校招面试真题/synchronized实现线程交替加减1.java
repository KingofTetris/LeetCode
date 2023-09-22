package 校招面试真题;

/**
 *
 * 使用synchronized实现一个线程+1，一个线程-1 交替进行。
 * @author by KingOfTetris
 * @date 2023/6/28
 */


class Share {
    private int number = 0;
    //加上synchronized会自动释放锁
    public synchronized void increase() throws InterruptedException {
        //if(number != 0) //虚假唤醒
        while (number != 0) { //注意这里要用while而不是if 这是为了避免虚假唤醒
            /**
             * 原因是
             * 在你的代码中，当其中一个线程调用 increase() 或 decrease()方法后，
             * 虽然会唤醒另一个线程，
             * 但没有确保另一个线程能够立即执行。这可能导致在某些情况下出现死锁或线程无法正确交替执行的问题。
             * 通过使用 while 循环而不是 if 条件判断，可以确保线程在被唤醒后再次检查条件，
             * 以避免虚假唤醒的问题。这样修改后，你的多线程代码应该能够实现 AA 和 BB 交替打印 10 次 1 和 0。
             * 说得通俗点，虚假唤醒只会判断第一次，之后就跳过了判断阶段
             */
            this.wait(); //不同于0就等 //在哪睡就在哪醒所以如果是if，那么他就直接走else分支去了，不会再判断
            //解决虚假唤醒的办法是把if改成while
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        this.notifyAll();//唤醒其他wait线程
    }

    public synchronized void decrease() throws InterruptedException {
        while (number != 1) {
            this.wait(); //同于0就等
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        this.notifyAll();//唤醒其他wait线程
    }
}

public class synchronized实现线程交替加减1 {
    public static void main(String[] args) {
        //两个线程争抢同一份资源。
        Share share = new Share();
        //创建多线程
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
    }
}
