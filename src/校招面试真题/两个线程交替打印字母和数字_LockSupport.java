package 校招面试真题;
import java.util.concurrent.locks.LockSupport;
/**
 * @author by KingOfTetris
 * @date 2023/9/6
 */
//LockSupport两个重要方法,park和unpark.
    //park阻塞自己，unpark等待别人发通行证给自己
public class 两个线程交替打印字母和数字_LockSupport {
    static Thread t1 = null, t2 = null;
    public static void main(String[] args) throws Exception{
        t1 = new Thread(()->{
            for(char p = 'A'; p <= 'Z'; p++) {
                System.out.print(p);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        },"'t2");
        t2 = new Thread(()->{
            for(int i = 1;i<=26;i++){
                LockSupport.park();
                System.out.print(i);
                LockSupport.unpark(t1);
            }
        }, "t1");
        t1.start();
        t2.start();
    }
}
