package LeetCode数据结构与算法基础.day5.树;

/**
 * @Author KingofTetris
 * @Date 2022/7/15 10:27
 * 字典树数据结构
 */
public class TrieNode {
    boolean val;
    TrieNode[] children = new TrieNode[26]; //字典树都有26个孩子 a - z

    /**
     * 带参构造
     * @param val
     */
    public TrieNode(boolean val){
        this.val = val;
    }

    /**
     * 无参构造
     */
    public TrieNode(){}

    /**
     * 往字典树里插入字符串word
     * @param word
     */
    public static void insert(TrieNode root,String word){
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if(root.children[i] == null) root.children[i] = new TrieNode();
            //节点下移
            root = root.children[i];
        }
    }

    /**
     * 搜索word是否在字典树中
     * @param root
     * @param word
     */
    public static boolean search(TrieNode root,String word){
        for (char c : word.toCharArray()) {
            int i  = c - 'a';
            //如果没有这个路径那就是false
            if (root.children[i] == null) return false;
            //如果存在就继续往下找
            root = root.children[i];
        }
        //路径存在
        return true;
    }

    /**
     * 寻找最短前缀匹配的单词
     * @param word
     * @return
     */
    public String shortestPrefixOf(TrieNode root,String word) {
        StringBuffer sb = new StringBuffer();
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            // 首次遇到有效路径，直接返回
            if (root.val){
                sb.append(c);
                return sb.toString();
            }
            // 路径不存在的情况，直接返回 ""
            if (root.children[i] == null) return "";
            root = root.children[i];
        }
        // 没找到
        return "";
    }

    /**
     * 含有通配符的检索
     * 顾名思义，.可以表示任何字符。比如：a.c是可以和[abc, aec]匹配的
     * @param node
     * @param pattern
     * @param index
     * @return
     */


}
