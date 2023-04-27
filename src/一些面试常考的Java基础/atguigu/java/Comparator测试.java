package 一些面试常考的Java基础.atguigu.java;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author KingofTetris
 * @File Comparator测试
 * @Time 2021/10/17  15:34
 */
public class Comparator测试 {
    @Test
    public void test(){
        int[][] intervals = {{1,2},{3,4},{5,9},{1,8},{1,4}};


        //lamda表达式 直接用 (o1,o2)表示对比对象 然后后面跟上比较设定
        Arrays.sort(intervals, (o1, o2) -> {
            //直接按左端点从小到大排序
            //如果要从大到小加个-号就行
            if (o1[0] != o2[0])
            return -(o1[0] - o2[0]);
            //如果左端点相同，再把右端点从小到大排。
            else
                return o1[1] - o2[1];
        });

        for(int[] item:intervals){
            for (int i = 0; i < item.length; i++) {
                System.out.print(item[i] + "\t");
            }
            System.out.println();
        }
    }
}
