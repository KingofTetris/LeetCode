package 每日一题;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @Author KingofTetris
 * @Date 2022/7/11 13:47
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词互不相同 。
 * 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 *
 * 实现 MagicDictionary 类：
 * MagicDictionary() 初始化对象
 * void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
 * bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中
 * 一个字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 *  
 *
 * 示例：
 *
 * 输入
 * ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
 * [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
 * 输出
 * [null, null, false, true, false, false]
 *
 * 解释
 * MagicDictionary magicDictionary = new MagicDictionary();
 * magicDictionary.buildDict(["hello", "leetcode"]);
 * magicDictionary.search("hello"); // 返回 False
 * magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
 * magicDictionary.search("hell"); // 返回 False
 * magicDictionary.search("leetcoded"); // 返回 False
 *  
 *
 * 提示：
 *
 * 1 <= dictionary.length <= 100
 * 1 <= dictionary[i].length <= 100
 * dictionary[i] 仅由小写英文字母组成
 * dictionary 中的所有字符串 互不相同
 * 1 <= searchWord.length <= 100
 * searchWord 仅由小写英文字母组成
 * buildDict 仅在 search 之前调用一次
 * 最多调用 100 次 search
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/implement-magic-dictionary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 实现一个魔法字典_2022_07_11 {

    @Test
    public void test(){
        MagicDictionary2 magicDictionary = new MagicDictionary2();
        String[] words = {"hello", "leetcode"};
        magicDictionary.buildDict(words);
        magicDictionary.search("hello"); // 返回 False
        magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
        magicDictionary.search("hell"); // 返回 False
        magicDictionary.search("leetcoded"); // 返回 False
    }
}



/**
 * 自己瞎写
 * 执行用时：39 ms 在所有 Java 提交中击败了38.03%的用户
 * 内存消耗：42 MB, 在所有 Java 提交中击败了73.63%的用户
 */
class MagicDictionary {

    LinkedList<String[]> magicDictionary; //放单词的列表
    public MagicDictionary() {
        magicDictionary = new LinkedList<>();
    }

    public void buildDict(String[] dictionary) {
        magicDictionary.add(dictionary);
    }

    public boolean search(String searchWord) {
        /**
         * 把searchWord的一个字母改为另一个字母，让searchWord和魔法字典中的单词匹配。
         * 既然只能改变一个单词。如果单词长度不对应就不用比了。
         */
        int length = searchWord.length();
        String[] words = magicDictionary.getFirst();//取出魔法字典
        int[] wordsLen = new int[words.length];//记录字典每个单词的长度
        for (int i = 0; i < words.length; i++) {
            wordsLen[i] = words[i].length();
        }
        /**
         * 然后只比较那些长度一样的单词，才有可能实现。
         * 记录下长度一样的单词下标。
         */
        int[] indexOfWords = new int[words.length];
        boolean flag = false;
        for (int i = 0; i < wordsLen.length; i++) {
            if (length == wordsLen[i]) {
                indexOfWords[i] = 1;//把长度一样的下标标记为1
                flag = true;//有长度一样的 才有可能可以成功转化
            }
        }
        if (flag == false) return false;//如果根本长度都不一样就不用比了。

        for (int i = 0; i < indexOfWords.length; i++) {
            if (indexOfWords[i] == 1){ //长度一样的单词去比较
                if (compareString(words[i],searchWord) == true){ //如果这两个单词没法做到转化就继续找下一个长度一样的,或者能转化就直接返回true
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断改变一个字母为另一个字母 两个单词会不会一样
     * 已经保证了word1和word2长度一样
     * @param word1
     * @param word2
     * @return
     */
    public boolean compareString(String word1,String word2){
//        if (word1.equals(word2)) return false;//根本就一样 也没法转换
        char[] word1CharArrary = word1.toCharArray();
        char[] word2CharArrary = word2.toCharArray();
        int differentCount = 0;//相同位置，不同的字母数量; 只能是1才行
        for (int i = 0; i < word1.length(); i++) {
            if (word1CharArrary[i] != word2CharArrary[i]){
                differentCount++;
            }
        }
        return differentCount == 1;
    }
}


/**
 * 后续改进 其实只需要一个一个去比较长度先，不一样就跳过。
 * 执行用时：27 ms 在所有 Java 提交中击败了 67.99%的用户
 * 内存消耗：42 MB, 在所有 Java 提交中击败了74.52%的用户
 *
 * 如果把compare里面的条件再补充一点，继续加快就会得到
 * 执行用时：26 ms 在所有 Java 提交中击败了 70.29%的用户
 * 内存消耗：41.7 MB, 在所有 Java 提交中击败了95.78%的用户
 *
 * 换成charAt
 * 执行用时：24 ms 在所有 Java 提交中击败了 73.88%的用户
 * 内存消耗：41.8 MB, 在所有 Java 提交中击败了84.64%的用户
 */
class MagicDictionary2 {
    LinkedList<String[]> magicDictionary; //放单词的列表
    public MagicDictionary2() {
        magicDictionary = new LinkedList<>();
    }

    public void buildDict(String[] dictionary) {
        magicDictionary.add(dictionary);
    }

    public boolean search(String searchWord) {
        int len = searchWord.length();
        String[] words = magicDictionary.getFirst();
        for (int i = 0; i < words.length; i++) {
            if ( len != words[i].length()) continue;
            else if (compareString(words[i],searchWord) == true){ //长度一样
                return true;//找到就返回true 还是不一样就继续
            }
        }
        return false; //全部匹配完都没有true 那就false
    }

    /**
     * 判断改变一个字母为另一个字母 两个单词会不会一样
     * 已经保证了word1和word2长度一样
     * @param word1
     * @param word2
     * @return
     */
    public boolean compareString(String word1,String word2){
//        if (word1.equals(word2)) return false;//根本就一样 也没法转换
//        char[] word1CharArrary = word1.toCharArray();
//        char[] word2CharArrary = word2.toCharArray();
        /**
         * 还可以用charAt节省点空间。怎么忘记了charAt方法
         */
        int differentCount = 0;//相同位置，不同的字母数量; 只能是1才行
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)){
                differentCount++; //这里还可以再快一点 如果有两处不一样就直接false
                if (differentCount > 1) return false; //大于1
            }
        }
        //当然最后也不能忽略 两个一样的情况 如果是0也返回false 1才是true
        return differentCount == 1;
    }
}