package 校招笔试真题.华为.华为实习0412;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/19
 * 在一个购物APP中，有一个核心购物系统，它的接口被 N个客户端调用。
 * 这些客户端负责处理来自不同渠道的交易请求，并将这些请求发送给核心购物系统。每个客户端有不同的调用量
 * R = [R1,R2,...,RN]，表示在一定时间内，
 * 这个客户端向核心购物系统发送的交易请求的数量。核心购物系统必须能够及时响应所有的请求，以确保交易顺利进行。
 * 然而，最近核心购物系统出现了集群故障，导致交易请求的处理速度变慢。
 * 为了避免系统崩溃，必须临时降级并限制调用量。具体而言，核心购物系统能接受的最大调用量为cnt
 * 如果客户端发送的请求总量超过cnt,则必须限制一些系统的请求数量，以确保核心购物系统不会超负荷工作。
 * <p>
 * 现在需要一个降级规则，来限制客户端的请求数量。规则如下：
 * 如果sum(R) <= cnt,正常调用，返回-1
 * 如果sum(R) > cnt,则必须设定一个阈值value,如果某个客户端发起的调用量超过value
 * 则该客户端的请求数量必须限制为value,其余未达到value的可以正常调用，要求求出最大的value(value可以为0)
 * <p>
 * 为了保证交易的顺利进行，必须保证客户端请求的数量不会超过核心购物系统的最大调用量，同时最大的value 要尽可能的大。
 * 需要高效地解决这个问题，以确保购物系统的高效性。
 * <p>
 * 输入描述
 * 第一行：每个客户端的调用量(整型数组)
 * <p>
 * 第二行：核心购物系统的最大调用量
 * <p>
 * 0 < R.length <= 1e5,0 <= Ri <= 1e5,0 <= cnt <= 1e9
 * <p>
 * 输出描述
 * 调用量的最大阈值
 * value
 * <p>
 * 样例
 * 样例一
 * 输入
 * <p>
 * 1 4 2 5 5 1 6
 * 13
 * 输出
 * <p>
 * 2
 * <p>
 * 因为sum(1 4 2 5 5 1 6)>13,将value设置为2则， sum(1,2,2,2,2,1,2)=12 < 13 所以最大value = 2
 * <p>
 * <p>
 * 样例二
 * 输入
 * <p>
 * 1 7 8 8 1 0 2 4 9
 * 7
 * 输出
 * <p>
 * 0
 * <p>
 * sum(1 7 8 8 1 0 2 4 9)>7,即使value = 1 sum(R) 也>7，只能设为0
 */
public class 购物系统的降级策略 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] s = line.split(" ");
        int[] nums = new int[s.length];
        long sum = 0;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(s[i]);
            sum += nums[i];
        }

        int cnt = sc.nextInt();
        if(sum <= cnt){
            System.out.println(-1);
            return;
        }

        //二分 TODO 强化训练一下二分查找，你连什么时候要用二分查找都还分不清楚
        int l = 0;
        int r = 100000;
        while(l < r){
            int mid = l + r + 1>> 1;
            long t = 0;
            for (int num : nums) {
                t += Math.min(num, mid);
            }
            if(t <= cnt) l = mid;
            else r = mid - 1;
        }
        System.out.println(l);
    }


    /**
     * 暴力模拟
     * 91，每次用max--的方式极限就是91了。AC得用二分。
     *
     * @param nums
     * @param cnt
     * @return 一个新知识点 数组一句话求和int sum = Arrays.stream(nums).sum(); JDK8的流新特性
     */
    public static int solution(int[] nums, int cnt) {
        int value = -1;
        int sum = 0;
        int flag = 0;
        int max = -1;
        Arrays.sort(nums); //先排序保证max最小
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);//取下当sum超过cnt时，最大的数。
            sum = sum + nums[i];
            if (sum > cnt) {
                flag = 1;
                break;
            }
        }

        //没过的原因在于上面max的取值。
        //如果取到了一个很大的max,那么每次仅仅用-1去循环尝试
        //最差就要尝试 max * nums.length 轮
        //max最大是 1e5,nums.length最大是1e5
        //那么最差情况就是 1e10 就过不了了。
        //为了让这个max尽量的小，还是得先排序一下。

        if (flag == 1) { //不满足普通调用就只能去找value了
            //拿到超过cnt时，最大的数max,那么就让大于max - 1的数字，都替换成max-1。然后计算sum，直到sum<cnt
            while (--max >= 0) {
                sum = 0;
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] > max) {
                        sum += max;
                    } else {
                        sum += nums[i];
                    }
                    if (sum > cnt) {
                        break;//如果sum已经大于cnt了，快速进入下一轮。减少计算
                    }
                }
                if (sum < cnt) {
                    return max;
                }
            }
        }
        return value;
    }

    /**
     * 二分查找 AC
     * 其实二分也是一样去找最大的max，但是二分每次查一半，比max--效率高得多
     *
     * @param nums
     * @param cnt
     * @return
     */
    public static int solution2(int[] nums, int cnt) {
        long sum = Arrays.stream(nums).sum();
        if (sum < cnt) return -1;



        //如果sum > cnt了，就要进行处理
        //二分
        Arrays.sort(nums);//先排序

        //去找max, max的范围是 [0,1e5]
        int left = 0;
        int right = (int) 1e5;

        while (left < right) {
            int mid = (left + right) / 2;//不存在溢出的问题，直接相加/2就行了
            //防溢出就用 left + (right - left) / 2;
            sum = 0;//重新计算sum
            for (int num : nums) {
                //判断当前元素和mid哪个小就加上哪个
                sum += Math.min(num, mid);
                if (sum > cnt) break; // 已经大于cnt了 就不用再浪费时间继续加了
            }

            //如果sum <= cnt,说明max还可以增大一点,提高下届left = mid
            if (sum <= cnt) left = mid;
            //sum > cnt，自然只能降低上界right = mid - 1
            else right = mid - 1;
        }

        return left;
    }
}
