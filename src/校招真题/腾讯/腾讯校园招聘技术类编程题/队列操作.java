package 校招真题.腾讯.腾讯校园招聘技术类编程题;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author KingofTetris
 * @Date 2023/4/3 16:48
 *
 * 数据结构基础之一——队列
 * 队列有五种基本操作，插入队尾、取出队首、删除队首、队列大小、清空队列。
 *
 * 现在让你模拟一个队列的操作，具体格式参考输入。
 *
 * 注意本题有多组输入。
 *
 * 数据范围： 0 <= n <= 1e5,读入的数val都满足 0<= val <= 1000
 *
 * 输入描述：
 * 第一行输入一个整数T，表示接下来有T组测试数据。
 * 对于每组测试数据：
 * 第一行输入一个整数Q，表示有Q次操作。
 * 接下来Q行，每行输入一种队列操作方式，具体格式如下：
 *
 * 初始状态下队列为空。
 *
 * 插入队尾：PUSH X
 * 取出队首：TOP//仅仅是看一下队首元素，不要把队首元素删除
 * 删除队首：POP
 * 队列大小：SIZE
 * 清空队列：CLEAR
 *
 * 1<=T<=100
 * 1<=Q,x<=1000
 * 保证操作为以上5种的任意一种。
 *
 * 输出描述：
 * 对于每组测试数据：
 * 如果操作为“取出队首”，输出队首元素，如果无法取出，输出“-1”
 * 如果操作为“删除队首”，如果无法删除，输出“-1”
 * 如果操作为“队列大小”，输出队列大小
 * 其他操作无需输出
 *
 * 输入例子：
 * 2
 * 7
 * PUSH 1
 * PUSH 2
 * TOP
 * POP
 * TOP
 * POP
 * POP
 * 5
 * PUSH 1
 * PUSH 2
 * SIZE
 * POP
 * SIZE
 * 输出例子：
 * 1
 * 2
 * -1
 * 2
 * 1
 */
public class 队列操作 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int n = 0;
        myQueue queue = new myQueue();
        while ( n < T ){
            int operationNum = sc.nextInt();
            for (int i = 0; i < operationNum; i++) {
                String deman = sc.nextLine();//这里只能用nextLine，如果用next() 一个空格就相当于一个next()
                String[] s = deman.split(" ");
                //Java8及以后是支持直接往switch里面放字符串的。会自行调用equals方法
                switch(s[0]){ //每个case后面都要加上break; 太久没写switch了
                    case "PUSH":
                        queue.push(Integer.valueOf(s[1]));
                        break;
                    case "POP":
                        queue.pop();
                        break;
                    case "TOP":
                        queue.top();
                        break;
                    case "SIZE":
                        queue.size();
                        break;
                    case "CLEAR":
                        queue.clear();
                        break;
                }
            }
            n++;
            sc.close();
        }
    }
}

class myQueue{
    //直接拿0当队头
    ArrayList<Integer> myQueue;
    myQueue(){
        myQueue = new ArrayList<>();
    }

    void push(int x){
        myQueue.add(x);
    }

    void pop(){
        if (myQueue.size() == 0) System.out.println(-1);//如果没有元素能出队
        else myQueue.remove(0); //删除队头
    }

    void top(){
        if (myQueue.size() == 0) System.out.println(-1);
        else {
            int head = myQueue.get(0);
            System.out.println(head);
        }
    }

    void size(){
        System.out.println(myQueue.size());
    }

    void clear(){
        while (myQueue.size() != 0){
            myQueue.remove(0);
        }
    }
}