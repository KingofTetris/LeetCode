package LeetCode数据结构入门.day2.字符串;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KingofTetris
 * @File 赎金信
 * @Time 2021/10/2  14:47
 */


/*给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，
判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。
如果可以构成，返回 true ；否则返回 false。

        (题目说明：为了不暴露赎金信字迹，
        要从杂志上搜索各个需要的字母，组成单词来表达意思。
        杂志字符串中的每个字符只能在赎金信字符串中使用一次。)

        示例 1：

        输入：ransomNote = "a", magazine = "b"
        输出：false
        示例 2：

        输入：ransomNote = "aa", magazine = "ab"
        输出：false
        示例 3：

        输入：ransomNote = "aa", magazine = "aab"
        输出：true
         

        提示：

        你可以假设两个字符串均只含有小写字母。*/

public class 赎金信 {


    @Test
    public void test(){
        String ransom = "aaeaadGM--";
        String magazine = "aaeeacsaGdM--a";

        boolean flag = canConstruct(ransom,magazine);
        System.out.println(flag);

    }

    //其实只要对比一下出现的次数就行了
    //这种缺点就是只能比较小写字母
//    public boolean canConstruct(String ransomNote, String magazine) {
//        int[] nums1 = new int[26];
//        int[] nums2 = new int[26];
//        for (int i = 0; i < ransomNote.length(); i++) {
//            nums1[ransomNote.charAt(i) - 'a']++;
//        }
//        for (int i = 0; i < magazine.length() ; i++) {
//            nums2[magazine.charAt(i) - 'a']++;
//        }
//
//        for (int i = 0; i < nums1.length; i++) {
//            if(nums1[i] > nums2[i])
//                return false;
//        }
//        return true;
//    }

    //用两个map来映射，最后对比一下 这种就什么都能比
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character,Integer> map_ransom = new HashMap<>();
        HashMap<Character,Integer> map_magazine = new HashMap<>();
        char[] toCharArray1 = ransomNote.toCharArray();
        char[] toCharArray2 = magazine.toCharArray();

        for (int i = 0; i < ransomNote.length(); i++) {
            map_ransom.put(toCharArray1[i],map_ransom.getOrDefault(toCharArray1[i],0) + 1);
        }
        for (int i = 0; i < magazine.length(); i++) {
            map_magazine.put(toCharArray2[i],map_magazine.getOrDefault(toCharArray2[i],0) + 1);
        }


        return compareMap(map_ransom,map_magazine);
        //现在要做的就是比较两个map里面相同的key的value值
        //只要map_magazine >= map_ransom就行


        /*for(Map.Entry<Character,Integer> map:map_ransom.entrySet()){
            System.out.println(map.getKey() + ":" + map.getValue());
        }

        System.out.println(); System.out.println();
        for(Map.Entry<Character,Integer> map:map_magazine.entrySet()){
            System.out.println(map.getKey() + ":" + map.getValue());
        }*/

    }

    public boolean compareMap(HashMap<Character,Integer> m1, HashMap<Character,Integer> m2){
        for(Map.Entry<Character,Integer> map:m1.entrySet()){
            int value1 = map.getValue();

            //要注意m2里面没用m1的元素的话 会报空指针异常要处理一下
            int value2 = m2.get(map.getKey())==null?0:m2.get(map.getKey());
            //只要有一个小于就返回false
            if(value2<value1)
                return false;
        }
        //全都大就返回true
        return true;
    }
}
