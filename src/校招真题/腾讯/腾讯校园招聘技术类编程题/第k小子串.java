package 校招真题.腾讯.腾讯校园招聘技术类编程题;

import java.util.*;

/**
 * @Author KingofTetris
 * @Date 2023/4/3 15:39
 * 输入一个字符串 s，s 由小写英文字母组成，保证 s 长度小于等于 5000 并且大于等于 1。
 * 在 s 的所有不同的子串中，输出字典序第 k 小的字符串。
 * 字符串中任意个连续的字符组成的子序列称为该字符串的子串。
 * 字母序表示英文单词在字典中的先后顺序，即先比较第一个字母，
 * 若第一个字母相同，则比较第二个字母的字典序，依次类推，则可比较出该字符串的字典序大小。
 *
 * 数据范围：0 <= |S| <= 5000,1 <= k <= 5
 *
 *  /**
 *      * 输入例子：
 *      * aabb
 *      * 3
 *      * 输出例子：
 *      * aab
 *      * 例子说明：
 *      * 不同的子串依次为：
 *      * a aa aab aabb ab abb b bb
 *      * 所以答案为aab
 * */
public class 第k小子串 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        int k = sc.nextInt();
        String s = kthSubstring(str,k);
        sc.close();
        System.out.println(s);
    }

    //最简单的办法当然是暴力找出所有子串，然后用字典序排序
    // ArrayList 有个方法 sort(Comparator.naturalOrder()) 就是按照字典序升序
    //找出第k小的子串
    //但是找全部子串，毫不意外得OOM了，超出O(n)的限制。

    //其实还可以改进一下，因为题目的K比较小，k<=5，其实你只需要维护子串<=5的就够了，其他更大的没必要加入
    //当然这样的做法通过是能通过，但不是最佳
    public static String kthSubstring(String str,int k){
        HashSet<String> strs = new HashSet<>(); //要记录不同的子串，所以用Set
        for(int i = 0;i < str.length();i++){
            int j = i + 1;
            /**
             * 至于为什么substring这个方法要采用左闭右开的区间
             * 原因在于
             * 1.与数组下标表示方式统一
             * 2.避免产生歧义，如果左闭右闭，那么i==j的时候，是表示空字符还是表示单字符呢？
             */
            while (j <= str.length()){ //取等号是为了取得字符串的末尾最后一个字符。
                if (j - i - 1 <= k ) //为什么要多减去1， 同样还是因为subString的原因，左闭右开，j-i的长度其实比指定字符串长度还要多1，所以要减去1
                {
                    String substring = str.substring(i, j);
                    strs.add(substring);
                    j++;
                }
                //比k还要长的子串就没必要存了
                else break;
            }
        }
        //因为Set是不支持排序的，那么还要多一步把它转化成ArrayList
        ArrayList<String> arrayStrs = new ArrayList<>();
        for (String s : strs) {
            arrayStrs.add(s);
        }
        //自然排序，也就是按照字典序升序
        arrayStrs.sort(Comparator.naturalOrder());
        return arrayStrs.get(k - 1);
    }


    /**
     * 链接：https://www.nowcoder.com/questionTerminal/c59d9690061e448fb8ec7d744c20ebff?orderByHotValue=1&page=2
     * 来源：牛客网
     * 首先我们需要一个有序的容器来存放子串，由于需要保证子串各不相同，因此还需要一个集合来对子串进行去重。
     * 由于子串的数量可能非常庞大，我们做一些优化，考虑到字典序第k小的子串长度一定不会超过k，
     * 因此在对长度进行遍历时，没有必要将候选的长度尝试到k以上。
     * 同时，我们还希望这个有序的容器能够以O(1)的复杂度获取到字典序第k小的子串。
     * 这里采用大根堆作为此有序容器，并将堆的大小控制为k，当堆中元素数量小于k时，
     * 直接往堆中插入元素；当堆中元素数量达到了k时，为了保证堆中是字典序最小的k个子串，
     * 我们仅在待入队子串的字典序小于堆顶子串时将堆顶子串出队，然后将待入队子串插入。
     * 如此一来，遍历完所有的子串后，直接取出堆顶元素即为字典序第k小的子串。
     * @param str
     * @param k
     * @return
     */
    public static String kthSubstring2(String str,int k){
        HashSet<String> strs = new HashSet<>(); //要记录不同的子串，所以用Set
        for(int i = 0;i < str.length();i++){
            int j = i + 1;
            while (j <= str.length()){ //因为substring[i,j) 所以要取等号
                String substring = str.substring(i, j);
                strs.add(substring);
                j++;
            }
        }
        //因为Set是不支持排序的，那么还要多一步把它转化成ArrayList
        ArrayList<String> arrayStrs = new ArrayList<>();
        for (String s : strs) {
            arrayStrs.add(s);
        }
        //自然排序，也就是按照字典序升序
        arrayStrs.sort(Comparator.naturalOrder());
        return arrayStrs.get(k - 1);
    }
}
