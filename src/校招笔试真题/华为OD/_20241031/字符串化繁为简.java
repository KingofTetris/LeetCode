package 校招笔试真题.华为OD._20241031;

import java.util.*;

public class 字符串化繁为简 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(getResult(s));
    }

    public static String getResult(String s) {
        // 主体字符容器
        StringBuilder sb = new StringBuilder();

        // 等效字符容器
        ArrayList<Character> list = new ArrayList<>();

        UnionFindSet ufs = new UnionFindSet(128);
        HashSet<Character> set = new HashSet<>();

        //  isOpen标志，表示有没有遇到 '(' 字符
        boolean isOpen = false;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                isOpen = true; // 接下来将开始收集等效字符
            } else if (c == ')') {
                isOpen = false; // 某个()内的等效字符收集完成

                if (list.isEmpty()) continue;

                char base = list.get(0);

                // 等效传递（利用并查集）
                for (Character letter : list) {
                    // 不同括号间的大小写字母可以形成等效传递, 比如括号1中 (abc), 括号2中 (AXY), 则两个括号的a,A形成等效传递，最终:a,b,c,A,X,Y互相等效
                    char upper = Character.toUpperCase(letter);
                    char lower = Character.toLowerCase(letter);

                    // set记录的是之前括号里面出现过的字母
                    if (set.contains(lower)) {
                        ufs.union(letter, lower); // 括号间等效传递（利用并查集）
                    }

                    if (set.contains(upper)) {
                        ufs.union(letter, upper); // 括号间等效传递（利用并查集）
                    }

                    ufs.union(letter, base); // 括号内等效传递
                }

                // 将当前()内字符加入set
                set.addAll(list);
                // 清空list, 用于收集下个()内的字母
                list.clear();
            } else if (isOpen) {
                list.add(c); // 等效字符容器
            } else {
                sb.append(c); // 主体字符容器
            }
        }

        // 等效替换
        char[] cArr = sb.toString().toCharArray();
        for (int i = 0; i < 128; i++) {
            char ch = ((char) i);
            char fa = ((char) ufs.find(i)); // 找到ch字母的等效的字典序最小的字母fa

            // 将ch替换为fa
            for (int j = 0; j < cArr.length; j++) {
                if (cArr[j] == ch) {
                    cArr[j] = fa;
                }
            }
        }

        String res = new String(cArr);
        return res.isEmpty() ? "0" : res; // 如果简化后的字符串为空，请输出为"0"。
    }
}

// 并查集实现
class UnionFindSet {
    int[] fa;

    public UnionFindSet(int n) {
        this.fa = new int[n];
        for (int i = 0; i < n; i++) fa[i] = i;
    }

    public int find(int x) {
        if (x != this.fa[x]) {
            this.fa[x] = this.find(this.fa[x]);
            return this.fa[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int x_fa = this.find(x);
        int y_fa = this.find(y);

        // 保证字典序小的字符优先为根
        if (x_fa < y_fa) {
            this.fa[y_fa] = x_fa;
        } else {
            this.fa[x_fa] = y_fa;
        }
    }
}
