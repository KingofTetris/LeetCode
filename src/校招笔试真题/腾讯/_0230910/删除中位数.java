package 校招笔试真题.腾讯._0230910;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/10
 */
public class 删除中位数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            ArrayList<Integer> a = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                a.add(sc.nextInt());
            }
            ArrayList<Integer> b = new ArrayList<>();
            for (int i = 0; i < n - 1; i++) {
                b.add(sc.nextInt());
            }
            String[] solution = solution(a, b);
            for (int i = 0; i < solution.length; i++) {
                if(i != solution.length - 1){
                    System.out.print(solution[i] + " ");
                }else {
                    System.out.println(solution[i]);
                }
            }
        }
    }

    public static String[] solution(ArrayList<Integer> a, ArrayList<Integer> b) {
        double[] res = new double[a.size()];
        int index = 0;
        //获得未排序的a
        ArrayList<Integer> origin = new ArrayList<>(a);
        int[] flags = new int[a.size()]; //用1标识数字已经删除
//        while (a.size() > 1){
        while (index < b.size()){
            //a排序后才能获得中位数
            ArrayList<Integer> aa = new ArrayList<>();
            //每次aa从origin里面取数
            for (int i = 0; i < origin.size(); i++) {
                if (flags[i] != 1){
                    aa.add(origin.get(i));
                }
            }
            aa.sort(null);
            int left = 0;
            int right = aa.size() - 1;
            double midValue;
            if (aa.size() % 2 != 0){
                int mid = (right + left) / 2;
                midValue = aa.get(mid);
            }else {
                int midL = (right + left) / 2;
                int midR = midL + 1;
                double midLV = aa.get(midL);
                double midRV = aa.get(midR);
                midValue = (midLV + midRV) / 2;
            }
            res[index] = midValue;
            //未排序的原数组a删除a[b[i]]
            //关键是这个未排序。
            //1->n b[i] 转化成下标是0-n-1 要减去1
//            a.remove(b.get(index) - 1);
            //flags标识即可
            flags[b.get(index) - 1] = 1;
            index++;
        }
        //res[n-1] = 最后一个没有被标识的ai
        int finalElem = 0;
        for (int i = 0; i < a.size(); i++) {
            if (flags[i] == 0) finalElem = i;
        }
        res[a.size() - 1] = origin.get(finalElem);
        //a排序后找出中位数
//        for (double re : res) {
//            System.out.print(re + "\t");
//        }
        //去掉后缀0，保留一位小数
        String[] nums = new String[res.length];
        for (int i = 0; i < res.length; i++) {
            String temp = String.valueOf(res[i]);
            if (temp.length() == 3 && temp.charAt(2) == '0'){
                nums[i] = temp.substring(0,1);//
            }
            if (temp.length() >= 3 && temp.charAt(2) != '0'){
                nums[i] = temp.substring(0,3);
            }
        }
     /*   for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }*/
        return nums;
    }

}
