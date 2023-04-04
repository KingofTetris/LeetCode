package 校招真题.腾讯.腾讯校园招聘技术类编程题;

import java.io.IOException;
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

/**
 * 插播一点Scanner类的基础知识
 * 1.next()方法以及形如nextXxx()的方法会忽略有效字符前的[空格]和[回车]，
 * 以空格和换行作为结束符，读取到有效字符后的空格和换行前结束，
 * 这就决定了【它们不能读取带空格的字符串】。
 * next()的返回值类型是String，nextXxx()的返回值是相应的基本数据类型。
 * 2.nextLine()方法只识别Enter键 ”\r" 作为结束，
 * 也就是说它获取回车键前的所有字符，包括空格。
 * nextLine()方法的返回值类型是String，【可以得到带空格的字符串】。
 * @param args
 */

/**因为前面有一个nextInt() nextInt()会把"\n"留在缓冲区导致nextLine直接读取到"\n"就结束，也就读取到空字符
 解决方法全部换成nextLine()，然后强转成数字
 也就是nextXxx() 不要和 nextLine() 直接连用。
 一般nextXxx()后如果要接上nextLine()，那么你要先用一次nextLine()吃掉缓冲区里面的回车
 nextInt会把"\r"留在缓冲区 nextLine()会把 "\r"也取走，而nextLine遇到"\r"就结束了。

 简单来说如果遇到输入包括字符串的，你就全部用nextLine()来读取。然后用xxx.parseXXX来强转
 **/
public class 队列操作 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        sc.nextLine();
        int n = 0;
        myQueue queue = new myQueue();
        while ( n < T ){
            int operationNum = Integer.parseInt(sc.nextLine());
            int i = 0;
            while (i < operationNum) {
                sc.nextLine();
                String command = sc.nextLine();
                //Java8及以后是支持直接往switch里面放字符串的。会自行调用equals方法
                if (command.startsWith("PUSH"))  {
                    String[] params = command.split(" ");
                    queue.push(Integer.parseInt(params[1]));
                }
                if ("POP".equals(command))   queue.pop();
                if ("TOP".equals(command))   queue.top();
                if ("SIZE".equals(command))  queue.size();
                if ("CLEAR".equals(command)) queue.clear();
                i++;
                System.out.println("当前i=" + i);
            }
            n++;
        }
        sc.close();
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