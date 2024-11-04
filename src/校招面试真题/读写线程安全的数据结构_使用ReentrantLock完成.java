package 校招面试真题;

import java.util.concurrent.locks.ReentrantLock;


//直接使用synchronized关键字锁住整个类的话会导致读读也是阻塞的。
//使用读锁和写锁进行改进。

public class 读写线程安全的数据结构_使用ReentrantLock完成 {
    private int data;
    private ReentrantLock readLock;
    private ReentrantLock writeLock;

    public 读写线程安全的数据结构_使用ReentrantLock完成() {
        data = 0;
        readLock = new ReentrantLock();
        writeLock = new ReentrantLock();
    }

    public int readData() {
        readLock.lock();
        try {
            return data;
        } finally {
            readLock.unlock();
        }
    }

    public void writeData(int newData) {
        writeLock.lock();
        try {
            data = newData;
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        读写线程安全的数据结构_使用ReentrantLock完成 dataStructure = new 读写线程安全的数据结构_使用ReentrantLock完成();

        // 线程安全地并发读写
        Runnable readWriteTask = () -> {
            int value = dataStructure.readData();
            System.out.println("Read value: " + value);

            int newValue = (int) (Math.random() * 100);
            dataStructure.writeData(newValue);
            System.out.println("Write value: " + newValue);
        };

        // 模拟多个线程对数据结构进行读写操作
        for (int i = 0; i < 5; i++) {
            new Thread(readWriteTask).start();
        }
    }
}
