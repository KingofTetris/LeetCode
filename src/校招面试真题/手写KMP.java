package 校招面试真题;

/**
 * @author by KingOfTetris
 * @date 2024/8/28
 */
public class 手写KMP {

    /**
     * 为什么 String.indexOf(substring) 不用KMP，而是采用暴力法
     *  因为 KMP的O(n+m)在实际应用并不那么理想，
     *  平均性能大都比不上Boyer-Moore 或 简化的 horspool算法，
     *  facebook 的folly库就采用Boyer-Moore算法，
     *  不过与KMP类似的Aho-Corasick在压缩空间后还是很可观的。
     *  Snort初期采用的是Aho-Corasick，但现在好像改用wu-manber了，
     *  太久没关注了。若想深入了解，可以去查阅这本书<< 柔性字符串匹配 >>
     *
     *  作者：楊.DL
     *      * 链接：https://www.zhihu.com/question/27852656/answer/38428419
     *      * 来源：知乎
     *      * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *  新的“优化”实现必须要在“常用场景”上能比当前实现有显著性能提升，而且额外空间开销不会很大，
     *  才有可能被接受。KMP在“常用场景”上未必能做到这点：初始化的时间开销、额外的空间开销都会成为绊脚石。
     *  所谓“常用场景”，Java里大多数String的长度其实并不长。有兴趣的话可以跑一个您的应用场景里的典型应用统计一下长度。
     *  实际上常用场景中，暴力法就特别适用了。 也就是正常情况下暴力法根本不会真的跑到O(m * n)，可放心食用。
     *
     * 作者：RednaxelaFX
     * 链接：https://www.zhihu.com/question/27852656/answer/38360968
     * 来源：知乎
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     */

    /**
     * KMP算法的实现，最重要的就是避免重复比较
     * 因此使用到了一个 最长相同前后缀数组  告诉你下一个比较下标应该从哪开始
     * 而不是一次一次挪一位，慢慢比较。那就和暴力没什么差别了。
     *
     * 但是KMP需要额外的O(t.length)的空间。
     *
     * @param s
     * @param t
     * @return
     */
    //当然面试还是要问你KMP的实现。唉，八股
    //前缀表（不减一）Java实现 s是否包含t，包含返回包含的首字母下标。
    public int indexOf(String s, String t) {
        if (t.length() == 0) return 0;
        int[] next = new int[t.length()];
        //KMP最重要的就是构造这个next数组
        getNext(next, t);

        //开始遍历s
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j > 0 && t.charAt(j) != s.charAt(i))
                //如果不相等，告诉算法要跳到哪个位置
                //也就是next[j-1]的位置，直到s[j] = s[i]
                j = next[j - 1];
            //相等就j后移
            if (t.charAt(j) == s.charAt(i))
                j++;
            //如果找到了完整的t
            if (j == t.length())
                //返回下标
                return i - t.length() + 1;
        }
        //遍历完了都找不到-1。
        return -1;

    }

    /**
     * 计算next数组
     * 也就是 最长相等前后缀长度 数组
     *
     * a a b a a f
     * 前缀：不包含尾字符的所有以第一个字符开头的所有连续子串。
     * a
     * a a
     * a a b
     * a a b a a
     * 后缀：不包含首字符的所有以最后一个字符结尾的所有连续子串。
     * f
     * a f
     * a a f
     * b a a f
     * a b a a f
     *
     * next其实就是以s[i]的最长相等前后缀长度
     * 比较特殊的s[0] 他既然是首字母又是尾字母
     * 相当于没有前后缀。next[0]就是0。
     *          j  i
     * 例如     a  a  b  a  a  f
     * next:   0  1  0  1  2  0
     * 在匹配的时候，比如f不匹配，他会跳到前一个字符的next值，也就是2，从b开始匹配
     * 跳过前缀"aa"，这是因为f前面的next为2，说明0-1这个前缀和后缀相等，那么相当于已经扫描过一遍，就没必要从头扫描了
     * 直接从b开始。
     * https://www.bilibili.com/video/BV18k4y1m7Ar/?spm_id_from=333.337.search-card.all.click&vd_source=299caa32bd4dc5f5ad17129611289250
     * @param next
     * @param s
     */
    private void getNext(int[] next, String s) {
        int j = 0;

       /** next其实就是以s[i]的最长相等前后缀长度
                * 比较特殊的s[0] 他既然是首字母又是尾字母
                * 相当于没有前后缀。next[0]就是0。
        * **/
        next[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            //如果s[i] ,s[j] 不相等，就把j一直往前跳到next[j-1]，直到s[i] = s[j]
            while (j > 0 && s.charAt(j) != s.charAt(i))
                j = next[j - 1];
            //直到s[i],s[j]相等，i,j一起往后走，加长最大相同前后缀的长度。
            if (s.charAt(j) == s.charAt(i))
                j++;
            next[i] = j;
        }
    }
}
