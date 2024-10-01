package LeetCode数据结构与算法基础.模拟;

import org.junit.Test;

import java.util.*;

/**
 * @Author KingofTetris
 * @Date 2022/7/7 14:31
 * 在英语中，我们有一个叫做 词根(root) 的概念，
 * 可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。
 * 例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 *
 * 现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。
 * 你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
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
        dictionary.add("a");
        dictionary.add("aa");
        dictionary.add("aaa");
        dictionary.add("aaaa");
        String sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa";
        String s1 = replaceWords1(dictionary, sentence);
        String s2 = replaceWords2(dictionary, sentence);
        System.out.println(s1);
    }

    /**
     * 所以考虑到先后顺序有区别的时候，可以用HashSet消除顺序的差异。
     * @param dictionary
     * @param sentence
     * @return
     */


    /**
     * 方法1 用word去遍历hashSet还是挺麻烦的。
     * @param dictionary
     * @param sentence
     * @return
     */
    public String replaceWords1(List<String> dictionary, String sentence) {
        //所以一开始hashSet其实也要自己去一次重复。如果词根B已经是词根A的延申了，就把词根B去掉。
        Set<String> dictionarySet = new HashSet<>();
        dictionarySet.add(dictionary.get(0));
        for (String rootB : dictionary) {
            //如果词根B已经是词根A的延申了，词根B就没必要进入set
            /**
             * 因为一边遍历，一边添加会发出concurrentModificationException。
             * 所以才需要用一个temp记录，遍历完再添加。
             */
            List<String> temp = new ArrayList<>();
            for (String rootA : dictionarySet) {
                if (!rootB.startsWith(rootA)){
                    temp.add(rootB);
                }
            }
            dictionarySet.addAll(temp);
        }
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            //如果word以某个词根开头，替换为这个词根。
            //注意有可能词根会重复比如a aa aaa。那就需要保证尽量最小的那个词根
            //比如aaaa则用a替换，而不是aa或者aaa
            for (String root : dictionarySet) {
                if (word.startsWith(root)){
                    words[i] = root;
                }
            }
        }
        return String.join(" ",words);
    }

    /**
     * 方法1 用hashSet反过来遍历word就方便很多。
     * @param dictionary
     * @param sentence
     * @return
     */
    public String replaceWords2(List<String> dictionary, String sentence){
        Set<String> dictionarySet = new HashSet<String>();
        for (String root : dictionary) {
            dictionarySet.add(root);
        }
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            //注意，反过来用set去遍历word.substring
            for (int j = 0; j < word.length(); j++) {
                //只要word碰到一个最短的词根就直接break
                //不能直接用word.startWith(root)
                //因为Set不保证遍历的时候一定是遍历最短的。
                if (dictionarySet.contains(word.substring(0, 1 + j))) {
                    words[i] = word.substring(0, 1 + j);
                    break;
                }
            }
        }
        return String.join(" ", words);
/*
        作者：力扣官方题解
        链接：https://leetcode.cn/problems/replace-words/solutions/1649109/dan-ci-ti-huan-by-leetcode-solution-pl6v/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
