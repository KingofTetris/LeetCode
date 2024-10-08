package 校招笔试真题.CVTE._20240906;

/**
 * 使用锁和flag来保证执行顺序
 */
public class 两个线程交替打印字母和数字_原生lock {
    private static final Object lock = new Object();
    private static boolean printAlphabet = true; //优先打印字母
    public static void main(String[] args) {
        new Thread(() -> {
            for (char c = 'A'; c <= 'Z'; c++) {
                synchronized (lock) {
                    while (!printAlphabet) {
                        try {
                            lock.wait();//如果标记为false，则打印数字，lock阻塞，释放lock。
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(c);
                    printAlphabet = false;
                    lock.notify();//继续请求lock
                }
            }
        }, "t1").start();
        new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                synchronized (lock) {
                    while (printAlphabet) {
                        try {
                            lock.wait();//如果标记为true，则打印字母，lock阻塞，释放lock。
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(i);
                    printAlphabet = true;
                    lock.notify();//继续请求lock
                }
            }
        }, "t2").start();
    }

}
