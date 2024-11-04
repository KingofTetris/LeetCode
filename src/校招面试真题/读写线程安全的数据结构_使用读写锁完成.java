package 校招面试真题;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class 读写线程安全的数据结构_使用读写锁完成 {
    private int data;
    private ReentrantReadWriteLock rwLock;

    public 读写线程安全的数据结构_使用读写锁完成() {
        data = 0;
        rwLock = new ReentrantReadWriteLock();
    }

    public int readData() {
        rwLock.readLock().lock(); //竞争读锁
        try {
            return data;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void writeData(int newData) {
        rwLock.writeLock().lock(); //竞争写锁
        try {
            data = newData;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        读写线程安全的数据结构_使用读写锁完成 dataStructure = new 读写线程安全的数据结构_使用读写锁完成();

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
