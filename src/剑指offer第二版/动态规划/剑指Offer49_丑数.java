package 剑指offer第二版.动态规划;

import org.junit.Test;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Author KingofTetris
 * @Date 2022/9/5 10:21
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 * 换个说法就是能被2,3,5里面任意一个数整除的数字
 * 就是所有因子中的质因子除1外，只能是2，3，5中的1-3个。不能是其他质因子。
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 * 1 是丑数。
 * n 不超过1690。
 *
 * 另外和第1201题丑数III比较，丑数的定义都不同了
 * 1201题的丑数指的只要因子包含a,b,c就是丑数。那么做法自然也不同了。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/chou-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer49_丑数 {

    /**
     * 求第n个丑数。
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {

        /**
         * 用小根堆存储每次取堆顶 + 集合去重  O(nlogn) O(n)
         * 从1开始，2x,3x,5x都是丑数，存入小根堆，但是存在重复的情况，
         * 所以先存入集合中去重，然后存入堆中
         */
        int[] factors = {2, 3, 5};//质因子
        Set<Long> seen = new HashSet<>();//存Long型防止溢出
        PriorityQueue<Long> heap = new PriorityQueue<>();
        //先存入1
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;//初始化是为了避免 n <= 0 从而出现异常
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();//每次取出堆顶
            ugly = (int) curr;
            for (int factor : factors) { //乘以每个质因子
                long next = curr * factor;
                //HashSet.add方法返回结果为boolean，成功为true,失败为false
                //如果HashSet添加next成功则说明不重复，添加进堆
                if (seen.add(next)) {
                    heap.offer(next); //堆的插入操作offer 会自动调整堆。O(logN)
                }
            }
        }
        return ugly;//返回堆顶即可
    }

    /**
     * DP：后面的丑数一定是前面的某个丑数*2，*3，*5得到的。
     * 假设前面的丑数是Xa,Xb,Xc 则 Xd = Min{Xa*2,Xb*3,Xc*5};
     * 每次得到相应最小的那么就要让对应的a b c++;为了避免重复
     * O(n) O(n)
     * @param n
     * @return
     */
    public int nthUglyNumber2(int n) {
        int a = 1,b = 1,c = 1;
        int dp[] = new int[n + 1];//一般dp取为 n + 1 舍弃掉0的位置 比较容易理解
        dp[1] = 1;//第一数是1
        for (int i = 2; i <= n ; i++) {//从第二个开始
            dp[i] = Math.min(Math.min(dp[a] * 2,dp[b] * 3),dp[c] * 5);//因为没有直接n个数取最小的API，所以先两两比较
            if (dp[i] == dp[a] * 2) a++;
            if (dp[i] == dp[b] * 3) b++;
            if (dp[i] == dp[c] * 5) c++;
        }
        return dp[n];//返回dp[n - 1]即可
    }


    /**
     * 简单递推关系，从1开始乘以a,b,c 并且用计数器num1,num2,num3来统计次数
     * 每次选最小的那个，乘以依次num1/2/3就++
     * 直到第n次即可。
     * 为什么解答错误？？
     * 下面的方法是错误的，下面的方法忽略了丑数的性质，
     * 丑数指的是只包含2，3，5作为质因子的数，丑数一定是前面的丑数的基础上x2,x3,x5得到的
     * 而不是num1,num2,num3去乘2，3，5得到的。
     * 例如2*7=14，用下面的算法也会被包含进入丑数，但是14的质因子显然多了个7。不符合定义
     */
  /*  public int nthUglyNumber(int n, int a, int b, int c) {
        int num1 = 1,num2= 1,num3 = 1;
        int uglyNumber = 1;
        for (int i = 1; i <= n; i++) {
             uglyNumber = Math.min(Math.min(num1 * a,num2 * b),num3 * c);
            if (uglyNumber == (num1 * a)) num1++;
            if (uglyNumber == (num2 * b)) num2++;
            if (uglyNumber == (num3 * c)) num3++;
        }
        //第n次以后的uglyNumber就是结果
        return uglyNumber;
    }*/

        @Test
    public void test(){
//        HashSet<Integer> set = new HashSet<>();
//        System.out.println(set.add(1));
//        System.out.println(set.add(1));
        剑指Offer49_丑数 s = new 剑指Offer49_丑数();
            System.out.println();
        }


    /**
     * 判断是否是丑数
     * @param n
     * @return
     */
    public boolean isUgly(int n) {

        //上面这种递归的写法好理解一点
        //因为每次至少是除以2 和下面的一样都不会超过O(logN)
        //但是递归栈的空间复杂度会多一点也是 O(logN)
        //第二种则是O(1)
        if(n<1)
            return false;
        if(n==1)
            return true;
        for(int ele:new int[]{2,3,5})
        {
            if(n%ele==0) //如果(n%ele)==0 就递归去求 (n/ele) 是不是丑数
                return isUgly(n/ele);
        }
        return false;
        /*if (n <= 0) {
            return false;
        }
        int[] factors = {2, 3, 5};
        for (int factor : factors) {
            while (n % factor == 0) {
                n /= factor;
            }
        }
        return n == 1; //除到最后如果不是1 就不是丑数*/

        /*作者：LeetCode-Solution
        链接：https://leetcode.cn/problems/ugly-number/solution/chou-shu-by-leetcode-solution-fazd/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
