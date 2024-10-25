package LeetCode数据结构与算法基础.手撕算法;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/10/25
 */
public class H指数 {


    //3,0,6,1,5
    //0 1 3 5 6

    //1 3 1
    //1 1 3
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        //发表h篇论文，且引用次数都要大于等于h
        //倒着遍历
        int h = 0;
        for (int i = citations.length - 1; i >= 0; i--) {
             if (citations[i] > h){
                 h++;
             }
             else if (citations[i] == h || citations[i] < h){
                 //不要再加了。
                 return h;
             }
        }
        return h;
    }
}
