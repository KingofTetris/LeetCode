package 校招笔试真题.腾讯._0230910;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/10
 */
public class 勇敢牛牛不怕苦难 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> nums = new ArrayList<>();
        while (n-- > 0) {
            nums.add(sc.nextInt());
        }
        long maxValue = 0;
        //最初战斗力是0,设置当前战斗力是x
        //如果x < ai 那么
        //牛牛会提升ai -x的战斗力
        //如果 x >= ai 那么牛牛会降低战斗力到ai.
        //那么经历所有战斗牛牛怎么样才能获得最多的勇气值?
        //战斗的顺序可以是任意序列的。
        //那么他应该一开始选择数组中的最大数进行战斗，获取最大的提升max - 0
        //此时战斗力变成max,那么他还想要获得勇气值就要变弱，去找最弱的min
        //把自己变成min 再去找max.
        //这样反复循环就能找到最大的勇气值。
        if (nums.size() == 1) {
            System.out.println(nums.get(0));
            return;
        }

        int round = 0;
        int now = 0;
        while (!nums.isEmpty()) {
            //每轮进行排序去找最弱和最强。
            nums.sort(null);
            //如果round % 2 == 0,那么就是要变强
            if (round % 2 == 0) {
                Integer max = nums.get(nums.size() - 1);
                maxValue += max - now;
                nums.remove(nums.size() - 1);
            }
            //round % 2 != 0 现在该变弱了
            else if (round % 2 != 0) {
                now = nums.get(0);
                nums.remove(0);
            }
            round++;
        }
        System.out.println(maxValue);
    }
}
