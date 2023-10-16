package 校招笔试真题.兴业银行.金融科技岗;

import java.util.ArrayList;

/**
 * @author by KingOfTetris
 * @date 2023/10/14
 */


//组合，经典回溯题
public class 数字组合 {

    public static void main(String[] args) {
        String s = "11313131";
        ArrayList<ArrayList<String>> result = translate(s);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
        System.out.println(result.size());
    }

    public static ArrayList<ArrayList<String>> translate(String s) {
        ArrayList<ArrayList<String>> groups = new ArrayList<>();
        //如果是集合里面套集合，那么方法模板就是这样。
        backtrack(s, 0, new ArrayList<>(), groups);
        return groups;
    }

    private static void backtrack(String s, int start, ArrayList<String> current,
                                  ArrayList<ArrayList<String>> groups) {
        if (start == s.length()) {
            //直接添加当前当前组合
            groups.add(new ArrayList<>(current));
            return;
        }

        //就这么简单，为什么你想不到???
        for (int i = start; i < s.length(); i++) {
            //取所有的子串start到i+1
            String substring = s.substring(start, i + 1);
            int num = Integer.parseInt(substring);
            //添加条件，1<=num<=26
            if (num >= 1 && num <= 26) {
                current.add(substring);
                //i + 1不重复
                backtrack(s, i + 1, current, groups);
                //回溯
                current.remove(current.size() - 1);
            }
        }
    }
}
