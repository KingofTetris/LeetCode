package LeetCode数据结构与算法基础.动态规划;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KingofTetris
 * @File 杨辉三角
 * @Time 2021/9/30  20:28
 */
/*   118. 杨辉三角
           给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。

           在「杨辉三角」中，每个数是它左上方和右上方的数的和。*/
//杨辉三角的数学性质
//第n行有n个数
//1.第n行的第m个数大小是C(n,m) 记作C(_n,^m)
//计算就是 n!/(n-m)!m!
//每个数字等于两肩之和 第n行第i个数 = 第n-1行i + 第n-1行i-1
//所以就是 C(n,i) = C(n-1,i) + C(n-1,i-1)
//根据这个去算
public class 杨辉三角 {


    @Test
    public void test() {
        List<List<Integer>> ret;
        ret = generate(30);
        for (int i = 0; i < ret.size(); i++) {
            //打印前面的空格
            for (int j = 0; j < ret.size() - i - 1; j++) {
                System.out.print("   ");
            }
            //填报数字直接的空格
            for (int j = 0; j <= i; j++) {
                System.out.print("   ");
                //printf控制格式化打印
                System.out.printf("%-3d", ret.get(i).get(j));
            }
            System.out.println();
        }
    }

    //最快的当然是直接存储整个杨辉三角直接打印就行
    //我们这里还是用计算的方式生成
    //ctrl+alt+u 树状图
    //ctrl+alt+b 查看实现该接口的类 或者直接去查jdk使用手册
    //
    public List<List<Integer>> generate(int numRows) {
        //ArrayList实现了List接口
        List<List<Integer>> ret = new ArrayList<>();
        //外层是行数0 --- numRows-1
        //内层是每行的元素的序号 0 --- i+1-1 = i
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            //下面这个for循环就是整个代码的核心
            for (int j = 0; j <= i; j++) {
                //开头结尾都是1
                if (j == 0 || j == i) {
                    //首先给开头结尾加上1
                    row.add(1);
                } else {
                    //第n行的第i个数就是上一行的两肩之和
                    /**也就是C(n,i) = C(n-1,i-1) + C(n-1,i)**/
                    //这就是杨辉三角的破译密码
                    // R(n,i) = R(n-1,i - 1) + R(n-1,i)
                    row.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
                }
            }
            //添加到集合中。
            ret.add(row);
        }
        return ret;
    }
}
