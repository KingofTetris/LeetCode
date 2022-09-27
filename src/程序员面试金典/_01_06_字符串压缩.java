package 程序员面试金典;

/**
 * @Author KingofTetris
 * @Date 2022/9/27 15:36
 */
public class _01_06_字符串压缩 {


    /**
     * 简单双指针
     * @param S
     * @return
     */
    public String compressString(String S) {

        StringBuffer sb = new StringBuffer();
        int j = 0;
        for (int i = 0; i < S.length();) {
             if (j < S.length() && S.charAt(i) == S.charAt(j) ) j++;
             else {//不等于
                 //放心else包括了 j == S.length()的情况 保证了末尾不出错
                 sb.append(S.charAt(i) + String.valueOf(j - i)); //到底是j - i 还是 j - i + 1 自己模拟一下就直到了
                 //直接让i 变到j
                 i = j;
             }
        }
        return sb.length() >= S.length() ? S : sb.toString();
    }
}
