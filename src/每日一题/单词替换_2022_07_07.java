package 每日一题;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author KingofTetris
 * @Date 2022/7/7 14:31
 * 在英语中，我们有一个叫做 词根(root) 的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 *
 * 现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 *
 * 你需要输出替换之后的句子。
 *
 * 示例 1：
 *
 * 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
 * 输出："the cat was rat by the bat"
 * 示例 2：
 *
 * 输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
 * 输出："a a b c"
 *  
 *
 * 提示：
 *
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写字母组成。
 * 1 <= sentence.length <= 10^6
 * sentence 仅由小写字母和空格组成。
 * sentence 中单词的总量在范围 [1, 1000] 内。
 * sentence 中每个单词的长度在范围 [1, 1000] 内。
 * sentence 中单词之间由一个空格隔开。
 * sentence 没有前导或尾随空格。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/replace-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 单词替换_2022_07_07 {
    /**
     * 首先将 dictionary 中所有词根放入哈希集合中，然后对于sentence 中的每个单词，
     * 由短至长遍历它所有的前缀，如果这个前缀出现在哈希集合中，则我们找到了当前单词的最短词根，
     * 将这个词根替换原来的单词。最后返回重新拼接的句子。
     *
     *
     */
    @Test
    public void test(){
        List<String> dictionary = new ArrayList<>();
        dictionary.add("cat");
        dictionary.add("bat");
        dictionary.add("rat");
        String sentence = "the cattle was rattled by the battery";
        String s = replaceWords(dictionary, sentence);
        System.out.println(s);
    }

    /**
     * 所以考虑到先后顺序有区别的时候，可以用HashSet消除顺序的差异。
     * @param dictionary
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        Set<String> dictionarySet = new HashSet<>();
        for (String root : dictionary) {
            dictionarySet.add(root);
        }
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                //从0到word.length-1。由短到长去匹配集合内的词根。 哈希集合的contains耗时O(w^2) w为word的长度
                if (dictionarySet.contains(word.substring(0,1+j))){ //用HashMap的contains来检测包含 就不会出现String.contains的情况了
                    words[i] = word.substring(0,1+j);
                    break;
                }
            }
        }
        return String.join(" ",words);
    }
}
