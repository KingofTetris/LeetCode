package 校招真题.华为.华为od_0408;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/4/14
 * 2023.04.08-华为od-第三题-最多等和子数组
 * 题目内容
 * 给定一个数组,从中选出若干个不相交的子数组，
 * 满足各个子数组的和都相等.这样称为一次合法筛选。
 * 塔子哥现在想选出尽量多的子数组，求这个最多的个数。前提是满足合法筛选哦~
 *
 * 输入描述
 * 第一行为N 1<= N <= 1000
 * 第二行为各个元素大小Ci ( -100000<= Ci <= 100000)
 * 输出描述
 * 一个整数M,表示满足要求的最多的组内子序列的数目
 *
 * 样例
 * 输入1
 * 10
 * 8 8 9 1 9 6 3 9 1 0
 * 输出1
 * 4
 * 说明:
 *
 * 选出和为9的子数组,4个子数组分别为[3,3],[5,5],[6,7],[8,8]，4个子数组
 *
 */


import java.util.*;


/**
 * 利用前缀和节省空间
 */
public class 最多等和子数组 {
    public static void main(String[] args) {
        //map<i, int[j,k]>表示区间和为i的区间共有j个，最后一个和为i的的子区间的最后一位下标是k(k从1开始)
        Map<Integer, int[]> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        //新建前缀和数组，方便快速计算区间和
        //利用前缀和来计算区间和。
        int[] nums = new int[n+1];

        for (int i = 1; i <= n; i++) {
            nums[i] = sc.nextInt();
            nums[i] += nums[i-1];
        }

       /**
         * 总体思路就是先求出所有子区间，
         * 然后用一个map遍历这些子区间的和
         * 1.如果是第一个直接，数量+1，记录下第一个子区间的右端点
         * 2.后面相等和子区间，需要判断左端点是不是比上一个子区间的右端点大，如果更大就可以更新，
         * 数量继续+1，更新末尾为当前子区间的右端点
         * 最后返回数量最大的那个子区间就可以了
         *
         * 细节就是数组从1到n，用前缀和求区间和，但其实直接用Map存也可以。
         * 不过用前缀和求可以节省空间到O(1)，用Map就是O(n^2)
         * 前缀和求区间和是倒过来求的，用nums[i] - nums[j] j从i-1到 0 表示的是区间[j+1,i]之和
         * 这就导致后面的判断条件，后一个左端点要大于前一个左端点的条件变成了 j >= ints[1]就可以了*/

        //枚举所有区间，复杂度O(n^2)
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0 ; j--) {
                //利用前缀和快速计算区间和
                //nums[i] - nums[j] 就是区间[j+1,i]的和
                int sum = nums[i] - nums[j];
                int[] ints = map.get(sum);
                if(ints == null){ //如果一开始是null 就填[1,i]
                    map.put(sum, new int[]{1, i});
                }else if(j >= ints[1]){
                    //如果新的区间左端点 > 上一个子社区的右端点 就不冲突了
                    //为什么取等号?因为区间和是[j+1,i]，即使取等号，后一个区间也不会和[xx,j]冲突
                    //说明区间之间不冲突，数量+1
                    //若无冲突则更新value，数量+1和结尾下标更改为j
                    map.put(sum, new int[]{ints[0] + 1, j});
                }
                //else 有冲突，不操作
            }
        }


        int res = 0;
        //最后遍历map输出数量最多的就行了
        for (int[] value : map.values()) {
            res = Math.max(res, value[0]);
        }
        System.out.println(res);
    }
}




/**
 * 不用前缀和用Map存储区间[i,j]以及它的和sum
 * 但是用map存在顺序问题，还是用上面的前缀和吧
 */
/*
public class 最多等和子数组 {
    public static void main(String[] args) {
        //如果你要用Map存区间，要用LinkedHashMap，按照顺序来遍历，不然会出错
        LinkedHashMap<int[],Integer> map = new LinkedHashMap<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n+1];
        //从1到n
        for (int i = 1; i <= n; i++) {
            nums[i] = sc.nextInt();
        }


        int sum;

        //找到所有区间以及他们的和
        for (int i = 1; i <= n; i++) {
            sum = 0;//每回合重置0
            for (int j = i; j <= n; j++) {
                sum += nums[j];
                map.put(new int[]{i,j},sum);
            }
        }

        //结果map res，和为Integer的子区间有多少个
        HashMap<Integer,int[]> res = new HashMap<>();
        //遍历map
        for(Map.Entry<int[],Integer> entry: map.entrySet()){
            //一开始为空的话就直接添加
            sum = entry.getValue();
            int[] interval = entry.getKey();
            if (res.get(sum) == null) res.put(sum,new int[]{interval[0],interval[1],1});
            //如果和为sum的已经有区间先到了
            else {
                //新的区间左端点要大于上一次区间的右端点
                //更新区间和数量
                if (interval[0] > res.get(sum)[1] ) res.put(sum,new int[]{interval[0],interval[1],res.get(sum)[2] + 1});
            }
        }

        //最后遍历values即可
        int max = 0;
        for(int[] values : res.values()){
            max = Math.max(values[2],max);
        }
        System.out.println(max);
    }
}*/
