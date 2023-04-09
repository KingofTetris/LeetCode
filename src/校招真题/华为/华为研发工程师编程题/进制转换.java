package 校招真题.华为.华为研发工程师编程题;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/4
 *
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。
 * 数据范围 1<= n <= 2^31 - 1
 */
public class 进制转换 {

    //直接调用api Integer.parseInt(x,radix)把x转化成10进制数字 radix表示x是什么进制，
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        //如果以为0x开头，那就要去掉0x
        int x ;
        if (s.startsWith("0x")){
             x = Integer.parseInt(s.substring(2,s.length()),16);//标明是16进制
        }
        else  x = Integer.parseInt(s,16);//标明是16进制
        System.out.println(x);
    }

    //当然正常是要你自己写个转化函数，比如下面这个
    public static int convertNum(String str)
    {
        String[] c={"A","B","C","D","E","F"};
        HashMap<String,Integer> hashmap= new HashMap<>();
        for(int i=0; i<=9;i++)
        {
            hashmap.put(i+"",i);//+"" 变成字符串
        }
        for(int j=10;j<=15;j++)
        {
            hashmap.put(c[j-10],j);
        }

        String[] st=new String[str.length()];
        for(int i=0;i<=str.length()-1;i++)
        {
            st[i]=str.substring(i,i+1);
        }

        int num=0;
        for(int i=2;i<=st.length-1;i++)
        //这里需要注意，如果输入是类似"1A"没有标识的16进制数，i的值从0开始
        //如果是"0x1A"这样有标识符的16进制，则在计算时，需要截掉0x这两位标识，i从2开始
        {
            num += hashmap.get(st[i]) * Math.pow(16,st.length-1-i);
        }
        return num;
    }

}
