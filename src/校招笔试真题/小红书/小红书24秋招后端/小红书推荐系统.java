package 校招笔试真题.小红书.小红书24秋招后端;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息

/**
 * 小红书有一个推荐系统，可以根据用户搜索的关键词推荐用户希望获取的内容。
 * 现在给定小红的搜索记录（记录为分词后的结果），我们认为当一个单词出现的次数不少于3次时，
 * 该单词为“用户期望搜索的单词”，即称为关键词。请你根据小红的记录，输出小红的用户画像对应的所有关键词。
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 256M，其他语言512M
 * 输入描述：
 * 一行字符串，仅由小写字母和空格组成。代表小红的搜索记录。
 * 字符串长度不超过100000。
 * 输出描述：
 * 小红所有的关键词。每行输入一个。你需要按照搜索频次从高到低输出。频次相同的，你需要按字典序升序输出。
 * 示例1
 * 输入例子：
 * kou red game red ok who game red karaoke yukari kou red red nani kou can koukou ongakugame game
 * 输出例子：
 * red
 * game
 * kou
 *
 *
 */
public class 小红书推荐系统 {

    //考排序
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        HashMap<String, Integer> map = new HashMap<>();
        String[] strs = s.split(" ");
        for (int i = 0; i < strs.length; i++) {
            String key = strs[i];
            //  System.out.println(key);
            map.put(key, map.getOrDefault(strs[i], 0) + 1);
        }

        // 将 HashMap 的 entrySet 转换为 List
        List<Map.Entry<String, Integer>> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 3 ) {
                res.add(entry);
            }
        }
        // 使用 lambda 表达式进行排序
        /**
         * 难点就是这个排序
         */
        //先按照频率降序，再按字典序升序
        //Collections.sort(xx,()->{}) 用集合工具类就这样写
        //列表可以直接排序。
        res.sort((e1, e2) -> {
            int compare = e2.getValue().compareTo(e1.getValue()); // 按照 value 的降序排列
            // 如果 value 相同，按照 key 的字典序升序排列
            if (compare == 0){
                return e1.getKey().compareTo(e2.getKey());
            }
            // 否则就返回正常就compare就行了
            return compare;
        });

        for (Map.Entry<String, Integer> entry : res) {
            System.out.println(entry.getKey());
        }
    }
}
