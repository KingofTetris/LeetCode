package JavaJunior.Thread;

/**
 * @author by KingOfTetris
 * @date 2024/9/21
 */
public class ThreadTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            System.out.print("Pong");
        });
        t1.run();
        System.out.print("Ping");
        System.out.println();
        for (int i = 0; i < 1000; i++) {
            Thread t2 = new Thread(()->{
                System.out.print("Pong");
            });
            t2.start();
            System.out.print("Ping");
            System.out.println();
        }
    }
}
