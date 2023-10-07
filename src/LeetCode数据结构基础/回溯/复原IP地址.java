package LeetCode数据结构基础.回溯;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/9/14
 */
public class 复原IP地址 {

    @Test
    public void test(){

    }

    List<String> res = new LinkedList<>();

    //给你一串字符串，请你判断能够复原的合法IP地址有哪些。
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() == 0) return res;
        //求组合问题 回溯
        String startS = "" + s.charAt(0);
        backtracking(s,startS,0);
        return res;
    }

    private void backtracking(String s,String startS,int start) {
        //终止条件
//        if (startS.length() == )
    }

}
