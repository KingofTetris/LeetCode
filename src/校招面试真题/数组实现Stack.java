package 校招面试真题;

public class 数组实现Stack {

    private int[] stackArray;
    private int top; // 栈顶指针
    private int capacity; // 栈容量

    public 数组实现Stack(int capacity) {
        this.capacity = capacity;
        this.stackArray = new int[capacity];
        this.top = -1; // 初始栈顶指针为-1，表示栈为空
    }

    // 压栈操作
    public void push(int item) {
        if (top == capacity - 1) {
            System.out.println("Stack is full, cannot push element.");
            return;
        }
        stackArray[++top] = item;
    }

    // 弹栈操作
    public int pop() {
        if (top == -1) {
            System.out.println("Stack is empty, cannot pop element.");
            return -1;
        }
        return stackArray[top--];
    }

    // 查看栈顶元素
    public int peek() {
        if (top == -1) {
            System.out.println("Stack is empty, no top element to peek.");
            return -1;
        }
        return stackArray[top];
    }

    public static void main(String[] args) {
        数组实现Stack stack = new 数组实现Stack(5);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println("Top element: " + stack.peek()); // Output: 3

        System.out.println("Popped element: " + stack.pop()); // Output: 3
        System.out.println("Popped element: " + stack.pop()); // Output: 2

        stack.push(4);
        stack.push(5);

        System.out.println("Top element: " + stack.peek()); // Output: 5
    }
}
