package LeetCode数据结构与算法基础.数学;

import org.junit.Test;

public class 十进制转M进制数 {

    @Test
    public void test(){
        int n = 100;
        String s = convertToBaseM(n,16);
        System.out.println(s);
    }

    public String convertToBase7(int n) {
        boolean flag = n < 0;
        if (flag) n = -n;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(n % 7);
            n /= 7;
        } while (n != 0);
        sb.reverse();
        return flag ? "-" + sb : sb.toString();
    }

    //其实这个就是通用的转M进制数 基本就是 n / m,直到n == 0然后倒推;
    public String convertToBaseM(int n,int m) {
        boolean flag = n < 0;
        if (flag) n = -n;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(n % m);
            n /= m;
        } while (n != 0);
        sb.reverse();
        return flag ? "-" + sb : sb.toString();
    }
}

/*
作者：宫水三叶
链接：https://leetcode.cn/problems/base-7/solutions/1316791/gong-shui-san-xie-jian-dan-mo-ni-ti-by-a-2759/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
