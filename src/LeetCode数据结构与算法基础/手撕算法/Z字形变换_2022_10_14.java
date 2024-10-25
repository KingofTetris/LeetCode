package LeetCode数据结构与算法基础.手撕算法;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2022/10/14
 */
public class Z字形变换_2022_10_14 {





    @Test
    public void test(){
        String s = "aksadjjkk";
        String convert = convert(s, 3);
        System.out.println(convert);
    }
    /**
     * 说是Z字形，看着更像N字形
     * numRows
     * 每个字符是按照s1 -> sN 再倒过来 sN -> s1的顺序填入
     *
     * @param s
     * @param numRows
     * @return
     */
    //这里没有要求打印Z字形，不然还要考虑空格，要麻烦一点。
    public String convert(String s, int numRows) {
        if(numRows < 2) return s; //n < 2 还是他自己
        List<StringBuffer> rows = new ArrayList<>();
        for(int i = 0; i < numRows; i++) rows.add(new StringBuffer());//每行添加一个空的sb

        int i = 0, flag = -1;
        for(char c : s.toCharArray()) {
            rows.get(i).append(c);//第i行 添加字符c
            if(i == 0 || i == numRows -1) flag = -flag; //第一行或者最后一行 就转向
            i += flag;//从上到下+1,从下到上-1
        }
        StringBuilder res = new StringBuilder();
        for(StringBuffer row : rows) res.append(row); //把每一行都添加到res里面
        return res.toString();

        /*作者：jyd
        链接：https://leetcode.cn/problems/zigzag-conversion/solution/zzi-xing-bian-huan-by-jyd/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
