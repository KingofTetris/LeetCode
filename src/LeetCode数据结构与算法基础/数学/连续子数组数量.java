package LeetCode数据结构与算法基础.数学;

import java.util.ArrayList;

/**
 * @Author KingofTetris
 * @Date 2023/3/31 11:39
 *
 * 给定一个数组，请你编写一个函数，返回元素乘积末尾零数量大于等于x的连续子数组数量
 * 答案可能很大，请将答案对 1e9 + 7取模再返回
 * 数组长度不超过 1e5,数组元素和x均不超过1e9
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 256M，其他语言512M
 * 示例1
 * 输入例子：
 * [5,2,3,50,4],2
 * 输出例子：
 * 6
 * 例子说明：
 * 共有以下6个合法连续子数组：
 * [5,2,3,50]，乘积为1500，末尾有2个零。
 * [5,2,3,50,4]，乘积为6000，末尾有3个零。
 * [2,3,50]，乘积为300，末尾有2个零。
 * [2,3,50,4]，乘积为1200，末尾有2个零。
 * [3,50,4]，乘积为600，末尾有2个零。
 * [50,4]，乘积为200，末尾有2个零。
 */


//双指针+滑动窗口？？？？
public class 连续子数组数量 {

    public static void main(String[] args) {
        int[] nums = {5,2,3,50,4};
        ArrayList<Integer> a = new ArrayList<>();
        for (int num : nums) {
            a.add(num);
        }
        连续子数组数量 sc = new 连续子数组数量();
        int subarrayNum = sc.getSubarrayNum(a, 2);
        System.out.println(subarrayNum);
    }
    static int MOD = (int) (1e9 + 7);


    /**
     * 首先要知道一个知识点.
     * n个数字相乘，末尾的0取决于 质因数 2 和 5 中数量较少的那个
     * 末尾的0的个数等于 2^n,5^m 其中较小者 也就是 min(n,m)
     * 知道这个这题就很简单了。
     */

    public int getSubarrayNum(ArrayList<Integer> a, int x) {
       int ans = 0;
       int cnt2,cnt5;
        for (int i = 0; i < a.size(); i++) {
            cnt2 = f(a.get(i),2);
            cnt5 = f(a.get(i),5);
            int j = i + 1;
            //如果从i到j以后满足条件了，那么从i到n-1肯定也满足
            while (j < a.size() && Math.min(cnt2,cnt5) < x){
                cnt2 += f(a.get(j),2);
                cnt5 += f(a.get(j),5);
                //例如5,2,3,50 乘积为1500，末尾有2个零。
                //那么不管后面你乘什么，注意num > 0，0的数量只会>=2，那么就不用再继续遍历了
                //直接break
                if (Math.min(cnt2,cnt5) >= x) break;//大于等于直接break,不用再j++
                j++;
            }
            //满足条件的连续子数组数量就是 a.size() - j
            ans = (ans + a.size() - j ) %  MOD;
        }
        return ans;
    }
    //计算x中有多少个因子p
    int f(int x, int p){
        int cnt = 0;
        while (x % p == 0){
            x = x / p;
            cnt++;
        }
        return cnt;
    }

}
