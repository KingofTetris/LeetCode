package 校招面试真题;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Customer {
    protected int money;

    public Customer(int money) {
        this.money = money;
    }

    public void withdraw() {
        System.out.println(Thread.currentThread().getName() + " withdraws " + money);
    }
}

class Window implements Runnable {
    private Customer[] customers;
    private static int totalWithdrawn = 0;

    public Window(Customer[] customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        for (Customer customer : customers) {
            customer.withdraw();
            totalWithdrawn += customer.money;
        }
        System.out.println("Total withdrawn from window " + Thread.currentThread().getName() + ": " + totalWithdrawn);
    }
}

public class _5个顾客取钱然后汇总 {
    public static void main(String[] args) {
        // 创建5个窗口
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Customer[] customers = new Customer[20];
        for (int i = 0; i < 20; i++) {
            customers[i] = new Customer(1); // 每个顾客取款20.汇总后应该是100
        }
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Window(customers)); // 每个窗口处理20个顾客的取款请求
        }
        executorService.shutdown(); // 关闭线程池，等待所有任务完成
    }
}
