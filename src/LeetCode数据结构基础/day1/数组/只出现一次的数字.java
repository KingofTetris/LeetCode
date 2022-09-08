package LeetCode数据结构基础.day1.数组;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 只出现一次的数字
 * @Time 2021/10/10  11:45
 */

/*给定一个非空整数数组，除了某个元素只出现一次以外，
其余每个元素均出现两次。找出那个只出现了一次的元素。

        说明：

        你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

        示例 1:

        输入: [2,2,1]
        输出: 1
        示例 2:

        输入: [4,1,2,1,2]
        输出: 4*/


    //其实和算法课上的筷子问题一样，找到那根没人匹配的筷子。
public class 只出现一次的数字 {

    @Test
    public void test(){
        int[] a = {4,1,2,1,2};
        System.out.println(singleNumber(a));
    }

    //使用额外空间可以用HashMap 记录 key-value 但时空消耗都很多。完全没有必要;
    //额外空间O(n) 时间复杂度要遍历数组还要判单MAP里有没有 也是O(n)
   /* public int singleNumber(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])){
                map.put(nums[i],1);
            }
            else{
                 map.put(nums[i],map.get(nums[i]) + 1);
             }
        }
        for(Map.Entry<Integer,Integer> item : map.entrySet()){
            if (item.getValue() == 1)
                return item.getKey();
        }
        //没有的话返回MAX_VALUE
        return Integer.MAX_VALUE;
    }*/

    //不要额外空间最简单想到的就是暴力法，标记数为flag
    //遍历数组里面的所有数，找不到就返回flag。时间为O(n^2)
    /*public int singleNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int flag = nums[i];
            int count = 0;
            for (int j = 0; j <nums.length; j++) {
                //用count记录一下相等出现的次数，自己和自己一定是1，大于1就是重复出现
                //如果找到一个数和它一样就直接跳出这次循环，找下一个数
                if(nums[j] == flag)
                {
                    count++;
                    if (count > 1) //大于1直接下一轮
                        break;
                }
                //如果遍历到最后一个数count还是1，那就是它。
                if (j == nums.length - 1){
                    if (count==1)
                        return flag;
                }
            }
        }
        //没有就返回最大值
        return Integer.MAX_VALUE;
    }*/

    //异或就是找茬，不同就是1，相同就是0
    //异或的特殊性质： 自己和自己异或为0，0和任何数异或都是他本身
    //异或运算又满足结合律，交换律。所以中间相同的数一异或全是0，最后只留下一个单独的数single
    public int singleNumber(int[] nums){
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }


}
