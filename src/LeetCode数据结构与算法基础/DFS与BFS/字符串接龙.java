package LeetCode数据结构与算法基础.DFS与BFS;

/**
 * @author by KingOfTetris
 * @date 2024/9/12
 */


import java.util.*;

/**
 * 题目描述
 * 字典 strList 中从字符串 beginStr 和 endStr 的转换序列是一个按下述规格形成的序列：
 * <p>
 * <p>
 * 1. 序列中第一个字符串是 beginStr。
 * <p>
 * 2. 序列中最后一个字符串是 endStr。
 * <p>
 * 3. 每次转换只能改变一个字符。
 * <p>
 * 4. 转换过程中的中间字符串必须是字典 strList 中的字符串，且strList里的每个字符串只用使用一次。
 * <p>
 * <p>
 * 给你两个字符串 beginStr 和 endStr 和一个字典 strList，
 * 找到从 beginStr 到 endStr 的最短转换序列中的字符串数目。如果不存在这样的转换序列，返回 0。
 * <p>
 * 输入描述
 * 第一行包含一个整数 N，表示字典 strList 中的字符串数量。
 * 第二行包含两个字符串，用空格隔开，分别代表 beginStr 和 endStr。
 * 后续 N 行，每行一个字符串，代表 strList 中的字符串。
 * <p>
 * 输出描述
 * 输出一个整数，代表从 beginStr 转换到 endStr 需要的最短转换序列中的字符串数量。
 * 如果不存在这样的转换序列，则输出 0。
 * <p>
 * <p>
 * 输入示例
 * 6
 * abc def
 * efc
 * dbc
 * ebc
 * dec
 * dfc
 * yhn
 * 输出示例
 * 4
 */
public class 字符串接龙 {


    /**
     * 1.题意就是从startString到endString是否存在一条路径
     * <p>
     * 而题目并没有直接给出两个字符串之间是否有连边而是隐含了。
     * <p>
     * 首先题目中并没有给出点与点之间的连线，而是要我们自己去连，条件是字符只能差一个。
     * <p>
     * 所以判断点与点之间的关系，需要判断是不是差一个字符，如果差一个字符，那就是有链接。
     * <p>
     * 2.有了图以后，从起点找终点的最短路径，可以DFS也可以BFS。
     *
     * @param args
     */
    public static void main(String[] args) {
        /* code */
        // 接收输入
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        String[] strs = sc.nextLine().split(" ");

        List<String> wordList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            wordList.add(sc.nextLine());
        }

        // wordList.add(strs[1]);

        // 打印结果
        int result = ladderLength(strs[0], strs[1], wordList);
        System.out.println(result);
    }

    private static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 使用set作为查询容器，效率更高
        HashSet<String> set = new HashSet<>(wordList);

        // 声明一个queue存储每次变更一个字符得到的且存在于容器中的新字符串
        Queue<String> queue = new LinkedList<>();

        // 声明一个hashMap存储遍历到的字符串以及所走过的路径path
        HashMap<String, Integer> visitMap = new HashMap<>();
        queue.offer(beginWord);
        visitMap.put(beginWord, 1);

        while (!queue.isEmpty()) {
            String curWord = queue.poll();
            int path = visitMap.get(curWord);
            for (int i = 0; i < curWord.length(); i++) {
                char[] ch = curWord.toCharArray();
                // 每个位置尝试26个字母
                for (char k = 'a'; k <= 'z'; k++) {
                    ch[i] = k; //修改ch的每个字符
                    String newWord = new String(ch);
                    //查看newWord是否等于end，如果找到end了，就结束，当前path+1
                    //如果找不到这条路径，就返回0
                    if (newWord.equals(endWord)) return path + 1;
                    // 如果这个新字符串存在于字典中
                    // 且之前未被访问到
                    if (set.contains(newWord) && !visitMap.containsKey(newWord)) {
                        //存在于这条路，则newWord入队,path + 1，重复这个过程。
                        visitMap.put(newWord, path + 1);
                        queue.offer(newWord);
                    }
                }
            }
        }

        return 0;
    }
}
