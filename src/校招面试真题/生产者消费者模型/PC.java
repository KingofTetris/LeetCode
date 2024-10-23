package 校招面试真题.生产者消费者模型;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by KingOfTetris
 * @date 2024/10/17
 */
//底层就是用一个队列和锁来控制。生产消费
public class PC {

    static class BlockingQueue<T> {

        // 队列
        private Deque<T> deque = new ArrayDeque<>();
        //容量
        private int capacity;

        //锁，定义为final，只能有一个
        private final ReentrantLock lock = new ReentrantLock();

        //生产和消费等待条件

        private Condition producerWaiset = lock.newCondition();

        private Condition consumerWaiset = lock.newCondition();


        //构造函数设置队列长度
        public BlockingQueue(int capacity) {
            this.capacity = capacity;
        }

        //然后是生产元素和消费元素
        public void put(T element) {
            //一上来先抢锁
            lock.lock();
            try {
                //队列已满，阻塞
                while (deque.size() == capacity) {
                    try {
                        producerWaiset.await();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //队列没满，放入消息
                deque.addLast(element);
                //唤醒消费者
                consumerWaiset.signal();
            } finally {
                //一定要释放锁
                lock.unlock();
            }
        }

        //消费
        public T take() {
            lock.lock();
            try {
                while (deque.size() == 0) {
                    try {
                        consumerWaiset.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                T res = deque.pollFirst();//消费
                //唤醒生产者
                producerWaiset.signal();
                return res;
            } finally {
                lock.unlock();
            }
        }
    }

    //测试pc模型
    public static void main(String[] args) {
        //初始化消息队列长度为3
        BlockingQueue<String> bq = new BlockingQueue<>(3);
        Thread producer = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "生产消息" + i);
                //生产消息
                bq.put("" + i);
                System.out.println("当前消息队列大小" + bq.deque.size());
            }
        },"producer");

        Thread consumer = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                //消费消息
                System.out.println(Thread.currentThread().getName() + "消费消息" + bq.take());
                System.out.println("当前消息队列大小" + bq.deque.size());
            }
        },"consumer");

        producer.start();
        consumer.start();
    }
}
