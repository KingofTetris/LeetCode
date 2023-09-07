package 校招笔试真题.小米.小米2023秋招0902;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;


//输入一个freq 。
//再输入一组数字 freq:loss
//保证输入的 freq不会重复
//请你找出和第一个freq最接近的freq然后返回他们的loss
//如果有多个则返回loss的平均值。
public class 小米的频率损失 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int freq = sc.nextInt();
        String line = sc.next();
        String[] datas = line.split(",");
        HashMap<String,String> map = new HashMap<>();
        for(String s : datas){
            String[] temp = s.split(":");
//             map.put(Integer.parseInt((temp[0]),Integer.parseInt(temp[1])));
            map.put(temp[0],temp[1]);
        }

        int distance = Integer.MAX_VALUE;
        double res = 0.0;
        //找到离freq最近的freq和其loss
        for(Map.Entry<String,String> entry : map.entrySet()){
            int key = Integer.parseInt(entry.getKey());
            //找到最近的key
            if(Math.abs(key - freq) <= distance){
                distance = Math.abs(key - freq);
            }
        }
        //在去找对应的loss
        double lossSum = 0;
        int count = 0;
        for(Map.Entry<String,String> entry : map.entrySet()){
            int key = Integer.parseInt(entry.getKey());
            if(Math.abs(key - freq) == distance){
                int value = Integer.parseInt(entry.getValue());
                lossSum += value;
                count++;
            }
        }
        if(count > 1){
            res = lossSum / count;
            System.out.println(res);
        }else{
            System.out.println(lossSum);
        }
    }
}
