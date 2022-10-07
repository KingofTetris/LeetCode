package LeetCode_HOT100题;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author KingofTetris
 * @Date 2022/10/4 17:03
 */
public class 电话号码的字母组合 {


    @Test
    public void test(){
        String digits = "23";
        List<String> strings = letterCombinations(digits);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> lists = new LinkedList<>();
        if (digits.equals("")) return lists;
        HashMap<Character, char[]> map = new HashMap<>();
        map.put('1',null);//1不对应任何字母
        map.put('2',new char[]{'a','b','c'});
        map.put('3',new char[]{'d','e','f'});
        map.put('4',new char[]{'g','h','i'});
        map.put('5',new char[]{'j','k','l'});
        map.put('6',new char[]{'m','n','o'});
        map.put('7',new char[]{'p','q','r','s'});
        map.put('8',new char[]{'t','u','v'});
        map.put('9',new char[]{'w','x','y','z'});



        /**
         * 先把初始符号加进去
         */
        char[] chars1 = map.get(digits.charAt(0));
        for (int j = 0; j < chars1.length; j++) {
            lists.add(String.valueOf(chars1[j]));
        }


        int count = 0;
        /**
         * 然后从1开始继续添加
         */
        for (int i = 1; i < digits.length(); i++) {
            /**
             * 取出list现有的字符串
             */
            while(count < lists.size() ){
                String s = lists.get(0);//每次都从0取，从0删
                lists.remove(0);//每次取到就要删去。以免留着重复
                char[] chars2 = map.get(digits.charAt(i));
                for (int j = 0; j < chars2.length; j++) {
                    lists.add(s + chars2[j] );//把s拼上新的chars
                    count++;
                }
            }
            count = 0;//每轮重新归零
        }

        return lists;
    }
}
