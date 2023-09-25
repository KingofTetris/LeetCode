package 校招笔试真题.星网锐捷;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2023/9/24
 */
public class 汉诺塔问题 {
    public static void main(String[] args) {

    }

    //三根柱子,left mid right
    //一开始所有盘子都在左边柱子上，自底向上从大到小。
    //现需把n个盘子挪到右边柱子。
    //规则是不能把大盘子放在小盘子上
    //有多少种挪法?

    ArrayList<String> res = new ArrayList<>();
    //3根柱子
    int[] left,mid,right;
    public ArrayList<String> getSolution (int n) {
        // write code here
        left = new int[n + 1];
        mid = new int[n + 1];
        right = new int[n + 1];
        //从上到下模拟圆盘
        for (int i = 1; i <= left.length; i++) {
             left[i] = i;
        }
        StringBuilder builder = new StringBuilder();
        backtracking(left,mid,right,builder);
        return res;
    }

    private void backtracking(int[] left, int[] mid, int[] right,StringBuilder builder) {
        //终止条件 right 有序
        if (isSort(right)){
            res.add(builder.toString());
            return;
        }
//        if (left)
    }

    private boolean isSort(int[] right) {
        for (int i = 1; i <= right.length; i++) {
            if (right[i] != i){
                return false;
            }
        }
        return true;
    }


}
