package 校招笔试真题.小米._20240905;

import java.util.*;

public class 塔子烤面包 {

    public static void main(String[] args){
        ArrayList<Integer> arr = new ArrayList<>();

//        arr.indexOf()
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        //第i台面包机考面包需要ai,bi的时间
        int[] a = new int[n];
        int[] b = new int[n];

        int minA = Integer.MAX_VALUE;
        int minB = Integer.MAX_VALUE;
        int indexA = -1;
        int indexB = -1;

        for(int i = 0;i < n;i++){
            a[i] = sc.nextInt();
            if(minA > a[i]){
                minA = a[i];
                indexA = i;
            }
        }
        for(int i = 0;i < n;i++){
            b[i] = sc.nextInt();
            if(minB > b[i]){
                minB = b[i];
                indexB = i;
            }
        }

        //每台面包机i烤面包a的时间是a[i],烤b的时间是b[i]
        //请问烤出a,b的最短时间是多少？

        //其实无非就两种情况，要么一个一个烤，要么一起烤，那么你算这两个结果
        //然后取min就好了。

        //一个就不用选了。
        if(n == 1){
            System.out.println(a[0] + b[0]);
            return;
        }

        if(indexA != indexB){
            //两个不同的面包机一起烤，就取时间长的就行了
            System.out.println(Math.max(minA,minB));
            return;
        }

        //如果一样，就一个一个烤
        if(indexA == indexB){
            System.out.println(minA + minB);
            return;
        }

    }
}
