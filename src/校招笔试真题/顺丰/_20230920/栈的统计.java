package 校招笔试真题.顺丰._20230920;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/20
 */
public class 栈的统计 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LinkedList<Integer> A = new LinkedList<>();
        LinkedList<Integer> B = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            A.add(sc.nextInt());
        }
        for (int i = 0; i < n; i++) {
            B.add(sc.nextInt());
        }
        getAllSolution(A, B, n);
        System.out.println(res);
    }
    //两种操作
    //1.A不为空，把A下标最小的元素压栈，删除最小元素
    //2.C不为空,设当前栈的元素个数是x，栈顶是y，则立即获得b_x * y的价值
    //然后把栈顶元素弹出。
    //现在小明有一种操作方案必须有2n次操作，且进行1操作时，保证A不为空，进行2操作 保证C不为空。
    //定义一种方案的价值是 b_x * y之和，请问不同的方案收益之和是多少？
    //当存在一个j(1<=j<=2n)，使得两个方案的第j次操作不同，就是不同的方案。
    /**
     * 2
     * 1 2
     * 2 3
     * 比如上面的A和B组成的方案收益之和为14.
     */
    static int res = 0;
    //栈C
    static LinkedList<Integer> stack = new LinkedList<>();

    public static void getAllSolution(LinkedList<Integer> a, LinkedList<Integer> b, int n) {
        backTracking(a, b, n, 0, 0);
    }

    private static void backTracking(LinkedList<Integer> a, LinkedList<Integer> b, int n, int curOpertions, int curSum) {
        //终止条件，如果操作数等于2n
        if (curOpertions == 2 * n) {
            res += curSum;
        }

        if (!a.isEmpty()){
            Integer first = a.getFirst();
            stack.push(first);
            a.pollFirst();
            curOpertions++;
            backTracking(a,b,n,curOpertions,curSum);
            curOpertions--;
            a.addFirst(first);
            stack.pop();
        }
        //2.C不为空,设当前栈的元素个数是x，栈顶是y，则立即获得b_x * y的价值
        //然后把栈顶元素弹出。
        if (!stack.isEmpty()){
            Integer peek = stack.peek();
            //第i个数的下标是i-1
            int val = b.get(stack.size() - 1) * peek;
            curSum += val;
            stack.pop();//栈顶出栈
            curOpertions++;
            backTracking(a,b,n,curOpertions,curSum);
            //还原
            curOpertions--;
            curSum -= val;
            stack.push(peek);
        }
    }


}
