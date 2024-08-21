package LeetCode数据结构与算法基础.day5.树;

import java.util.*;

public class 腾讯20230910_最小化频率的删除代价 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        //使得f(a)最小的删除代价是多少
        int[] arr = new int[n];
        HashMap<Integer,Integer> map =  new HashMap<>();
        for(int i = 0;i < n;i++){
            arr[i] = sc.nextInt();
            //统计各自出现的数量
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        //填充出现次数
         ArrayList<Integer> arrayList = new ArrayList<>(map.values());
         //大根堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> o2 - o1);
        for (int i = 0; i < arrayList.size(); i++) {
            pq.add(arrayList.get(i));
        }

        int count = 0;

        //找到f(a)第二大的那个出现次数
        //把第一大通过删除变成第二大的
        //如果都变成第二大的还有剩余，就继续变第三
        //一直重复这个过程去找最小f(a)
        while (!pq.isEmpty() && count < k) {
            int curr = pq.poll();//取出最大次数
            if (curr > 0) {
                curr--;
                count++;//一直到count = k 或者 cur < 0 没得删了
                pq.add(curr);
            } else {
                break;
            }
        }
        int res1 = pq.peek();//就是最小f(a)
        int res2 = 0;//代价
        for (int i = 0; i < n; i++) {
            int freq = map.get(arr[i]);
            if (freq > res1) {
                res2 += i+1;
                map.put(arr[i], freq - 1);
            }
        }
        System.out.println(res1 + " " + res2);
    }
}
