package 校招笔试真题.虾皮._20240924;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/9/24
 */
public class 找出特殊用户 {


    @Test
    public void test(){
        int n = 3;
        int[][] r = {{1,3},{2,3},{3,2}};
        int i = FindSpecialUser(n, r);
        System.out.println(i);
    }
    public int FindSpecialUser(int n, int[][] relations) {
        // write code here
        int[] gz = new int[n + 1];
        for (int[] relation : relations) {
            gz[relation[1]]++;
        }
        for (int i = 1; i <= n; i++) {
            if (gz[i] == n - 1){
                return i;
            }
        }

        return -1;
    }
}
