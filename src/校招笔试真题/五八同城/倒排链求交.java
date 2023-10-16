package 校招笔试真题.五八同城;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author by KingOfTetris
 * @date 2023/10/14
 */
public class 倒排链求交 {



    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 倒排链求交，并倒序输出
     * @param docList1 int整型一维数组 第一个关键词对应帖子的倒排链
     * @param docList2 int整型一维数组 第二个关键词对应帖子的倒排链
     * @return int整型一维数组
     */
    public int[] intersectionAndSort (int[] docList1, int[] docList2) {
        // write code here
        int n = docList1.length;
        int m = docList2.length;
        if (n < m){
            intersectionAndSort(docList2,docList1);
        }
        ArrayList<Integer> res = new ArrayList<>();
        ArrayList<Integer> doc1 = new ArrayList<>();
        ArrayList<Integer> doc2 = new ArrayList<>();
        for (int i = 0; i < docList1.length; i++) {
            doc1.add(docList1[i]);
        }
        for (int i = 0; i < docList2.length; i++) {
           doc2.add(docList2[i]);
        }
        for (int i = 0; i < doc2.size(); i++) {
            if (doc1.contains(doc2.get(i))){
                res.add(doc2.get(i));
            }
        }
        int[] nums = new int[res.size()];
        res.sort(Comparator.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            nums[i] = res.get(i);
        }
        return nums;
    }

}
