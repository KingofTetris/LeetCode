package 校招面试真题;


//synchronized保证线程安全。
public class 简单FIFO_用数组实现 {
    private int[] array; // 存储队列元素的数组
    private int capacity; // 队列最大容量，也就是数组容量。
    private int size; // 队列当前包含的元素数量
    private int head; // 队列头部指针
    private int tail; // 队列尾部指针

    // 初始化FIFO队列
    public 简单FIFO_用数组实现(int capacity) {
        this.capacity = capacity;
        this.array = new int[capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    // 向队列中添加元素
    public boolean enqueue(int item) {
        if (size == capacity) {
            return false; // 队列已满，无法添加元素
        }

        array[tail] = item; // 将元素添加到队列尾部
        tail = (tail + 1) % capacity; // 更新尾部指针，循环利用数组空间
        size++; // 更新队列元素数量
        return true; // 添加成功
    }

    // 从队列中取出元素
    public Integer dequeue() {
        if (size == 0) {
            return null; // 队列为空，无法取出元素
        }

        int item = array[head]; // 从队列头部取出元素
        head = (head + 1) % capacity; // 更新头部指针，循环利用数组空间
        size--; // 更新队列元素数量
        return item; // 返回取出的元素
    }

    public static void main(String[] args) {
        简单FIFO_用数组实现 fifo = new 简单FIFO_用数组实现(5);

        fifo.enqueue(1);
        fifo.enqueue(2);
        fifo.enqueue(3);

        System.out.println(fifo.dequeue()); // Output: 1
        System.out.println(fifo.dequeue()); // Output: 2

        fifo.enqueue(4);
        fifo.enqueue(5);

        System.out.println(fifo.dequeue()); // Output: 3
        System.out.println(fifo.dequeue()); // Output: 4
        System.out.println(fifo.dequeue()); // Output: 5
        System.out.println(fifo.dequeue()); // Output: null
    }
}
