package 校招笔试真题.小米._20240905;

import java.util.*;

public class 操作序列 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();

        //删除一个数，或者给某个数+1
        //使得序列之和为x的倍数
        int[] nums = new int[n];

        int sum = 0;

        ArrayList<Integer> arr = new ArrayList<>();

        for(int i = 0;i < n;i++){
            nums[i] = sc.nextInt();
            sum += nums[i];
        }

        System.out.println(sum);

        if(sum % x == 0){
            System.out.println("0");
            return;
        }
        //先从小到大排序
//        Arrays.sort(nums);

        for(int i = 0;i <n;i++){
             arr.add(nums[i]);
        }

        //先确认sum是x的倍数的哪边。
        //比如sum是 7  x是5
        //那么 5 < 7 < 10

        int k = sum / x;//先找到下限。
        int leftKX = k * x;
        while(leftKX < sum){
            k++; //找到第一个比sum大的x的倍数
            leftKX = k * x;
        }

        leftKX = (k - 1) * x;
        int rightKX = k * x;

        System.out.println("【" + leftKX + "," + rightKX + "】");
        int res = 0;
        //现在就可以找sum离哪边近了。
        int diff1 = sum - leftKX;
        int diff2 = rightKX - sum;

        System.out.println(diff1);
        System.out.println(diff2);

        if(diff1 < diff2){
            while(diff1 != 0){
            if(arr.contains(diff1)){
            //找到第一个diff1，然后删掉他
              arr.remove(arr.indexOf(diff1));
              res++;
              System.out.println(res);
              return;
            }
            //如果没有diff1
            //那么就只能去找个数+1，然后diff1--
            else{
                diff1--;
                res++;
                if(diff1 == 0){
                    System.out.println(res);
                    return;
                }
            }
            }
        }
        //如果right更近点。那么就只能+1 那就是diff2;
        if(diff1 > diff2){
            System.out.println(diff2);
            return;
        }
    }
}
