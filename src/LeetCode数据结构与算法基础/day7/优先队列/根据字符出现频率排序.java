package LeetCode数据结构与算法基础.day7.优先队列;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author KingofTetris
 * @File 根据字符出现频率排序
 * @Time 2021/10/30  10:48
 *
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 示例 1:
 *
 * 输入:
 * "tree"
 *
 * 输出:
 * "eert"
 *
 * 解释:
 * 'e'出现两次，'r'和't'都只出现一次。
 * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
 * 示例 2:
 *
 * 输入:
 * "cccaaa"
 *
 * 输出:
 * "cccaaa"
 *
 * 解释:
 * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
 * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
 * 示例 3:
 *
 * 输入:
 * "Aabb"
 *
 * 输出:
 * "bbAa"
 *
 * 解释:
 * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
 * 注意'A'和'a'被认为是两种不同的字符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 根据字符出现频率排序 {
    @Test
    public void test(){
        String s = "dhasldms";
        String res = frequencySort(s);
        System.out.println(res);
    }

    //模仿前k个高频元素，一样的写法。
    //所以这两题给出了优先队列的用法之一就是给map排序，可以按key 也可以按value 当然结果放在了优先队列中
    public String frequencySort(String s) {
        HashMap<Character,Integer> freq = new HashMap<>();
        for(int i = 0;i < s.length();i++){
            char temp = s.charAt(i);
            freq.put(temp,freq.getOrDefault(temp,0) + 1);
        }
        PriorityQueue<Map.Entry<Character,Integer>> queue;
        queue = new PriorityQueue<>(
                (o1,o2) -> -(o1.getValue() - o2.getValue())
        );
        queue.addAll(freq.entrySet());
        StringBuffer sb = new StringBuffer();
        while(!queue.isEmpty()){
            Map.Entry<Character,Integer> temp = queue.poll();
            for(int i = 0;i < temp.getValue();i++){
                sb.append(temp.getKey());
            }
        }
        return sb.toString();
    }
}
