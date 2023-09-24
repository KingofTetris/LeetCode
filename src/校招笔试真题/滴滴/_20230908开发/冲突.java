package 校招笔试真题.滴滴._20230908开发;

/**
 * @author by KingOfTetris
 * @date 2023/9/8
 */
import java.util.*;

public class 冲突{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        LinkedList<String> list = new LinkedList<>();
        while(t-- > 0){
            list.add(sc.next());
        }
        solution(list);
    }
    //暴力法，每个字符串去和其他字符串两两拼接
    //然后尝试删除前后缀，看看能不能得到自己。如果不能就是false,可以就是true
    public static void solution(LinkedList<String> list){
        int n = list.size();
        int[] flags = new int[n];//初始全部默认是0 不可以
        for(int i = 0;i<n;i++){
            String s1 = list.get(i);
            for(int j = i;j<n;j++){
                String s2 = list.get(j);
                //有一次成功就可以保留下来了。
                if(isSatisfied(s1,s2)){
                    flags[i] = 1;
                }
            }
        }
        //现在我们要去除flags[i] = 0的字符串
        String[] ss = new String[n];
        for(int i = 0;i<n;i++){
            if(flags[i] == 1){
                ss[i] = list.get(i);
            }
        }
        LinkedList<String> finalRes = new LinkedList<>();
        for(String s : ss){
            if(s != null && s.length() != 0){
                finalRes.add(s);
            }
        }
        finalRes.sort(Comparator.naturalOrder());
        System.out.println(finalRes.size());
        for(String s : finalRes){
            System.out.println(s);
        }
    }
    public static boolean isSatisfied(String s1,String s2){
        //拼接有两种方式
        String res1 = s1 + s2;
        String res2 = s2 + s1;
        int len = res1.length();
        //然后判断能否通过去除前后缀来得到s1
        //至少要去除1个前后字母
        String ac1 = res1.substring(1,len - 1);
        String ac2 = res2.substring(1,len - 1);
        //如果包含就能通过去除前后缀来获得s1，那么s1就是符合要求的。
        if(ac1.contains(s1) || ac2.contains(s1)){
            return true;
        }
        //不然的话就是false
        return false;
    }
}












