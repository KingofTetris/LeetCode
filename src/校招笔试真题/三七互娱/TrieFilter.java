package 校招笔试真题.三七互娱;

/**
 * @author by KingOfTetris
 * @date 2023/9/17
 */
public class TrieFilter {
    //问用户输入的字符s 是否包含了敏感词s1到sN? 要求效率最快
    private TrieNode root;

    public TrieFilter() {
        root = new TrieNode();
    }

    public void addSensitiveWord(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.containsKey(c)) {
                node.put(c, new TrieNode());
            }
            node = node.get(c);
        }
        node.setEnd();
    }

    public boolean containsSensitiveWord(String s) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            if (node.containsKey(c)) {
                node = node.get(c);
                if (node.isEnd()) {
                    return true; // 包含敏感词
                }
            } else {
                node = root; // 重置到根节点
            }
        }
        return false; // 不包含敏感词
    }

    public static void main(String[] args) {
        TrieFilter trieFilter = new TrieFilter();

        // 添加敏感词
        trieFilter.addSensitiveWord("cnm");
        trieFilter.addSensitiveWord("nmb");
        // ...

        // 需要检查的字符串
        String s = "cccsswnmnmbsmb";

        // 判断字符串是否包含敏感词
        boolean containsSensitiveWord = trieFilter.containsSensitiveWord(s);
        if (containsSensitiveWord) {
            System.out.println("该字符串包含敏感词");
        } else {
            System.out.println("该字符串不包含敏感词");
        }
    }
}

class TrieNode {
    private TrieNode[] links;
    private boolean isEnd;

    public TrieNode() {
        links = new TrieNode[26]; // 假设只包含小写字母a-z
    }

    public boolean containsKey(char c) {
        return links[c - 'a'] != null;
    }

    public TrieNode get(char c) {
        return links[c - 'a'];
    }

    public void put(char c, TrieNode node) {
        links[c - 'a'] = node;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd() {
        isEnd = true;
    }
}
