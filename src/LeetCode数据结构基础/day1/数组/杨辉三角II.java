package LeetCode数据结构基础.day1.数组;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KingofTetris
 * @File 杨辉三角II
 * @Time 2021/10/13  21:56
 */

//输出杨辉三角的rowIndex行的元素
public class 杨辉三角II {

//    给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
//
//    在「杨辉三角」中，每个数是它左上方和右上方的数的和。
    //杨辉三角的性质：第N行有N个数
    //每个数是两肩之和  就是[j] + [j - 1]
    @Test
    public void test(){
        List e = getRow(3);
        for(Object value:e){
            System.out.println(value);
        }
    }


    //
    //注意这里的rowIndex从0开始 所以要多在外层循环+1 才能get(rowIndex)
    public List<Integer> getRow(int rowIndex) {
            List<List<Integer>> ret = new ArrayList<>();
        //外层是行数0 --- numRows-1
        //内层是每行的元素的序号 0 --- i+1 -1 = i
        for (int i = 0; i < rowIndex + 1; i++) {
            List<Integer> row = new ArrayList<>();
            //下面这个for循环就是整个代码的核心
            for (int j = 0; j <= i; j++) {
                //开头结尾都是1
                if(j == 0 || j == i){
                    row.add(1);
                }
                //其他情况就是两肩之和 [j] + [j-1]
                else{
                    row.add(ret.get(i-1).get(j-1) + ret.get(i-1).get(j));
                }
            }
            ret.add(row);
        }
        //list从0开始
        return ret.get(rowIndex);
    }

}
