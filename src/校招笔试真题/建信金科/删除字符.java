package 校招笔试真题.建信金科;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author by KingOfTetris
 * @date 2023/10/14
 */
public class 删除字符 {

    @Test
    public void test(){
        String s = "abc";
        int i = Delete_character(s);
        System.out.println(i);
    }

    //0.6
    public int Delete_character (String s) {
        // write code here
        HashMap<Character,Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c,map.getOrDefault(c,0) + 1);
        }
        //至少要删除多少个字符才能让map中各个value就都不相同?
        HashSet<Integer> set = new HashSet<>();
        int res = 0;
        for (Integer value : map.values()) {
            boolean add = set.add(value);
            //如果添加失败说明有重复
            while (!add){
                value--;
                res++;
                //如果value = 0 说明已经删光了
                //没有必要添加到set中。0是可以重复的。
                if (value == 0){
                    break;
                }
                //再次添加。
                add = set.add(value);
            }
        }
        return res;
    }
}
