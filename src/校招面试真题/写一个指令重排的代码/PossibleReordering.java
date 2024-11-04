package 校招面试真题.写一个指令重排的代码;

public class PossibleReordering {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    /**
     * 大家都直到可能的结果有(1,0) (0,1) (1,1)
     * 为什么会存在0，0呢??
     * 大多数现代微处理器都会采用将指令乱序执行（out-of-order execution，简称OoOE或OOE）的方法，
     * 在条件允许的情况下，直接运行当前有能力立即执行的后续指令，
     * 避开获取下一条指令所需数据时造成的等待。通过乱序执行的技术，处理器可以大大提高执行效率。
     * 除了处理器，常见的Java运行时环境的JIT编译器也会做指令重排序操作，
       即生成的机器指令与字节码指令顺序不一致。
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread one = new Thread(() -> {
            a = 1;
            x = b;
        });

        Thread other = new Thread(() -> {
            b = 1;
            y = a;
        });
        one.start();
        other.start();
        one.join();
        other.join();
        System.out.println("(" + x + "," + y + ")");
    }
}
