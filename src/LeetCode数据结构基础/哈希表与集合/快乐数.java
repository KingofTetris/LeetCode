package LeetCode数据结构基础.哈希表与集合;

/**
 * @author by KingOfTetris
 * @date 2023/9/11
 */


import java.util.HashMap;

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 *
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 *
 * 示例：
 *
 * 输入：19
 * 输出：true
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class 快乐数 {
    public static void main(String[] args) {
        System.out.println(isHappy(Integer.MAX_VALUE));
    }

    public static boolean isHappy(int num){
        int lastRes = num;
        //怎么避免无限循环？
        //无限循环，一定是sum重复出现吗？
        //用map来记录是否重复出现。
        //你怎么证明快乐数陷入无限循环，一定是因为出现周期性无解的变化而不是无限地增大下去呢？？
        //因为num的MAX = Integer.MAX_VALUE
        //这个数字第一次计算快乐值仅仅是260。是不可能无限增大下去的。
        HashMap<Integer,Integer> map = new HashMap<>();
        while (true){
            int curRes = 0;
            while (lastRes > 0){
                int n = lastRes % 10;
                lastRes = lastRes / 10;
                curRes += n * n;
            }
            if (curRes == 1){
                return true;
            }
            lastRes = curRes;
            System.out.println(lastRes);
            map.put(lastRes,map.getOrDefault(lastRes,0) + 1);
            //如果有值重复出现了，那么就会无限循环。
            if(map.containsValue(2)){
                return false;
            }
        }
    }
}
