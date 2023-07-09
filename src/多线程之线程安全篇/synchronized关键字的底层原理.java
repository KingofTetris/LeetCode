package 多线程之线程安全篇;

/**
 * @author by KingOfTetris
 * @date 2023/6/28
 */
public class synchronized关键字的底层原理 {

    public static void main(String[] args) {
        TicketDemo ticketDemo = new TicketDemo();
        for (int i = 0; i < 200; i++) {
//            new Thread(ticketDemo::getTicket).start();//创建200个线程去抢票
            //上面是lambda表达式，现在java更推荐你用方法引用。
            new Thread(ticketDemo::getTicket).start();//创建200个线程去抢票
        }
    }
}



class TicketDemo{
    int ticketNum = 100; //票数
    static final Object LOCK = new Object();//单例对象锁
    public void getTicket(){
        synchronized (LOCK){
            //synchronized是一个互斥的对象锁，同一时间只能有一个线程获取。其他线程得不到就只能BLOCKED。
            //当前方法释放对象锁以后才能获取。
            if (ticketNum <= 0){
                System.out.println("余票不足");
            }
            else {
                ticketNum--;//非原子性操作
                System.out.println(Thread.currentThread().getName()+"抢到了一张票,剩余"+ticketNum + "张");
            }
        }
        //逻辑代码结束就会自动对象释放锁。
    }
}
