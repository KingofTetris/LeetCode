package 校招面试真题.生产者消费者模型;

/**
 * @author by KingOfTetris
 * @date 2024/10/17
 */


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 为什么要有生产消费模型
 * 1.生产者，消费者解耦。生产者只需要考虑生产消息，无需关系消费者如何接受消息；消费者同理
 *
 * 2.数据缓冲。如果短时间内，生产者生产消息的速率远远大于消费者消费的速率，
 * 那么多余的消息会缓冲在容纳消息的容器内（消息队列），等待消费者消费
 *
 * 3.支持并发。生产者，消费者可以由多个线程同时生产或消费。消息队列通过互斥锁控制生产、消费行为，保证线程安全。
 * ————————————————
 *
 *                             版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
 *
 * 原文链接：https://blog.csdn.net/qq_62835094/article/details/136515703
 */
public class 生产者消费者模型 {
    // 消息队列（阻塞队列）
    static class BlockingQueue<T> {
        // 队列
        private Deque<T> deque = new ArrayDeque<>();
        // 容量
        private int capacity;
        // 锁
        private final ReentrantLock lock = new ReentrantLock();
        // 消费者等待条件
        private Condition consumerWaitSet = lock.newCondition();
        // 生产者等待条件
        private Condition producerWaitSet = lock.newCondition();

        public BlockingQueue(int capacity) {
            this.capacity = capacity;
        }

        // 添加元素
        public void put(T element) {
            lock.lock();
            try {
                // 队列已满
                while (deque.size() == capacity) {
                    try {
                        // 阻塞等待
                        producerWaitSet.await();
                    } catch (InterruptedException e) {
                    }
                }
                // 添加元素
                deque.addLast(element);
                // 唤醒消费者
                consumerWaitSet.signal();
            } finally {
                lock.unlock();
            }
        }

        // 获取元素
        public T take() {
            lock.lock();
            try {
                // 判空
                while (deque.size() == 0) {
                    try {
                        // 阻塞等待
                        consumerWaitSet.await();
                    } catch (InterruptedException e) {
                    }
                }
                // 获取元素
                T res = deque.pollFirst();
                //唤醒生产者
                producerWaitSet.signal();
                return res;
            } finally {
                lock.unlock();
            }
        }
    }


    //模拟长度为3的消息队列
    public static void main(String[] args) {
        BlockingQueue<String> bq = new BlockingQueue<>(3);
        // 创建生产者
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                //模拟生产者生产消息，每秒生产一个消息
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("生产: " + i);
                bq.put("" + i);
            }
        }, "producer");

        // 创建消费者
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费: " + bq.take());
            }
        }, "consumer");

        producer.start();
        consumer.start();
    }
/*————————————————

    版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。

    原文链接：https://blog.csdn.net/qq_62835094/article/details/136515703*/
}
