package 校招笔试真题.FunPlus._20240913;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author by KingOfTetris
 * @date 2024/9/13
 */
public class 顺子 {

    @Test
    public void test(){
        ArrayList<Integer> pokes = new ArrayList<>();
        int[] nums = {1,10,11,12,13};
        for (int i = 0; i < nums.length; i++) {
            pokes.add(nums[i]);
        }
        boolean straight = isStraight(pokes);
        System.out.println(straight);
    }

    public boolean isStraight (ArrayList<Integer> pokes) {
        HashSet<Integer> set = new HashSet<>();
        //牌的大小在0-13
        int max = -1;
        int min = 14;
        for (int num : pokes) {
            if (num == 0) continue;//0跳过
            if (set.contains(num)) return false;//重复则不可能。
            set.add(num);
            max = Math.max(max,num);
            min = Math.min(min,num);
        }
        //这种漏掉了，10,11,12,13,1的情况，要特殊处理
        //13,1,8,0,0 ? 这种怎么办?
        return max - min < 5;
    }
}
