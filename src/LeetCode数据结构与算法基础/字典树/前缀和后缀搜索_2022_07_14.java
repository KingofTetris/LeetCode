package LeetCode数据结构与算法基础.字典树;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author KingofTetris
 * @Date 2022/7/15 10:42
 * 设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
 *
 * 实现 WordFilter 类：
 *
 * WordFilter(string[] words) 使用词典中的单词 words 初始化对象。
 * f(string pref, string suff) 返回词典中具有前缀 prefix 
 * 和后缀 suff 的单词的下标。如果存在不止一个满足要求的下标，返回其中 最大的下标 。
 * 如果不存在这样的单词，返回 -1 。
 *  
 *
 * 示例：
 *
 * 输入
 * ["WordFilter", "f"]
 * [[["apple"]], ["a", "e"]]
 * 输出
 * [null, 0]
 * 解释
 * WordFilter wordFilter = new WordFilter(["apple"]);
 * wordFilter.f("a", "e"); // 返回 0 ，因为下标为 0 的单词：前缀 prefix = "a" 且 后缀 suff = "e" 。
 *  
 * 提示：
 *
 * 1 <= words.length <= 104
 * 1 <= words[i].length <= 7
 * 1 <= pref.length, suff.length <= 7
 * words[i]、pref 和 suff 仅由小写英文字母组成
 * 最多对函数 f 执行 104 次调用
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/prefix-and-suffix-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 前缀和后缀搜索_2022_07_14 {
}

/**
 * 字典树求出前缀树和后缀树
 */
class WordFilter {
    class TrieNode {
        List<Integer> list = new ArrayList<>();
        TrieNode[] children = new TrieNode[26];
    }

    private TrieNode prefix = new TrieNode();
    private TrieNode suffix = new TrieNode();

    public WordFilter(String[] words) {
        build(prefix, words, true); //构建前缀树
        build(suffix, words, false); //构建后缀树
    }

    public int f(String pref, String suff) {
        List<Integer> prefList = query(prefix, pref, true);
        List<Integer> suffList = query(suffix, suff, false);
        int i = prefList.size() - 1, j = suffList.size() - 1;//两个列表的下标
        /**
         * 从后往前找两个有序列表的交点，也就是最大下标。
         */
        while (i >= 0 && j >= 0) {
            // 注意：比较 Integer 类变量最好不要直接比较，自动拆箱成 int 后再比较
            int l1 = prefList.get(i), l2 = suffList.get(j); //双指针
            if (l1 == l2) return l1; //相等当然就返回这个值了，如果不相等，哪个比较大哪个就减1
            else if (l1 > l2) i--;
            else j--;
        }
        return -1;
    }


    /**
     * 构建前缀树和后缀树 构建字典树的复杂度是O(∑ m_i ^ 2) m_i是每个单词的长度
     * 通过isPref来区分前后
     * @param root
     * @param words
     * @param isPref
     */
    private void build(TrieNode root, String[] words, boolean isPref) {
        for (int i = 0; i < words.length; i++) {
            TrieNode p = root;
            int len = words[i].length();
            for (int j = isPref ? 0 : len - 1; j >= 0 && j < len; j = isPref ? j + 1 : j - 1) {
                int cur = words[i].charAt(j) - 'a'; //当前单词的第j个位置对于的ASCII - 'a'的ASCII 前缀从0开始，后缀从Len-1开始
                if (p.children[cur] == null) p.children[cur] = new TrieNode();
                p = p.children[cur];

                /**
                 * 以[them, zip, team, the, app, that]这个样例为基础
                 * 前缀树存 t 的节点 root.children['t' - 'a'].list就会重复them,team,the,that四次
                 * 对应的下标就是[0,2,3,5]
                 *
                 * 后缀树存 m 则root.children['t' - 'a'].list重复them,team两次
                 * 对应的下标是 [0,2]
                 * 那么有了这两个前后缀列表，现在的问题是找到两个有序链表的公共交点并且下标最大，所以我们可以反过来从末尾找
                 * 最后就能快速找到以 t 开头，以 m 结尾的下标最大的单词 team
                 */
                p.list.add(i); //多了一步把对于的下标存储到节点i里面
            }
        }
    }

    /** 搜索前后缀的复杂度是 O(max(p,s)) 其中 p 和 s 分别是输入的pref 和 suff 的长度。
     * 在前后缀树里寻找匹配的单词下标列表
     * @param root
     * @param s
     * @param isPref
     * @return
     */
    private List<Integer> query(TrieNode root, String s, boolean isPref) {
        TrieNode p = root;
        int len = s.length();
        for (int i = isPref ? 0 : len - 1; i >= 0 && i < len; i = isPref ? i + 1 : i - 1) {
            int cur = s.charAt(i) - 'a';
            if (p.children[cur] == null) return new ArrayList<>();//如果不匹配就返回空列表
            p = p.children[cur];//匹配就继续往下找
        }
        return p.list;//找到了以s开头或结尾的单词就返回对应的单词在数组中的下标列表
    }
}
