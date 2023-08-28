package 校招笔试真题.腾讯.腾讯音乐娱乐集团2023技术类;

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
       int cnt2 = 0,cnt5 = 0;
        for (int i = 0; i < a.size(); i++) {
            cnt2 = f(a.get(i),2);
            cnt5 = f(a.get(i),5);
            int j = i + 1;
            //如果从i到j以后满足条件了，那么从i到n-1肯定也满足
            while (j < a.size() && Math.min(cnt2,cnt5) < x){
                cnt2 += f(a.get(j),2);
                cnt5 += f(a.get(j),5);
                if (Math.min(cnt2,cnt5) >= x) break;//大于等于直接break,不用再j++
                j++;
            }
            //直接用a.size() - j 就是从i开始满足条件的连续子数组的数量
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

    public int getSubarrayNum_violence (ArrayList<Integer> a, int x) {
        // write code here
        int ans = 0;
        double product = 1;
        for (int i = 0; i < a.size(); i++) {
            product = a.get(i); //每轮更新成当前的数字
            int zeroNum = zeroNum(product);
            if (zeroNum >= x) ans++;
            for (int j = i + 1; j < a.size(); j++) {
                product = (product * a.get(j)); //暴力法乘积会上溢出，对 1e9+7 取余0就没有了。这怎么解决？
                zeroNum = zeroNum(product);
                if (zeroNum >= x) ans++;
            }
        }

        return ans;
    }

    //计算num末尾有多少个0
    public int zeroNum(double num){
        int ans = 0;
        while (num % 10 == 0){
            ans++;
            num = num / 10;
        }
        return ans;
    }

    public int getSubarrayNum_GPT(ArrayList<Integer> a, int x) {
        int n = a.size();
        int[] prefix = new int[n];
        prefix[0] = a.get(0);
        int cnt = 0;
        if (prefix[0] == 0) cnt++;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * a.get(i);
            if (prefix[i] % 10 == 0) {
                int zeros = countTrailingZeros(prefix[i]);
                if (zeros >= x) cnt++;
            }
        }
        for (int i = 1; i < n; i++) {
            int j = i - 1;
            while (j >= 0) {
                int p = prefix[i] / prefix[j];
                if (p % 10 == 0) {
                    int zeros = countTrailingZeros(p);
                    if (zeros >= x) cnt++;
                }
                j--;
            }
        }
        return cnt;
    }

    private int countTrailingZeros(int n) {
        int cnt = 0;
        while (n % 10 == 0) {
            cnt++;
            n /= 10;
        }
        return cnt;
    }

}
