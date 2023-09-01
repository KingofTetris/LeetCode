package 校招笔试真题.小米.小米2022秋招;

/**
 * @author by KingOfTetris
 * @date 2023/9/1
 */
public class 合并有序数组 {


    //a的长度就是m + n,前面m位是b的长度，就是专门用来存放b的
    //下面是不使用额外空间的做法
    public int[] solution(int[] a, int[] b) {
        int lb = b.length;
        int la = a.length - lb;
        int cur = a.length - 1;
        //A = [1,6,7,0,0,0]
        //B = [2,4,6]
        while (lb > 0 || la > 0) {
            if (a[la - 1] > b[lb - 1]) {
                a[cur] = a[--la];
            } else {
                a[cur] = b[--lb];
            }
            cur--;
        }
        if (la == 0) {
            while (lb > 0) {
                a[cur--] = b[--lb];
            }
        }
        return a;
    }

  /*  作者：小暴龙🐉
    链接：https://www.nowcoder.com/exam/test/72969342/submission?examPageSource=Company&pid=39932492&testCallback=https%3A%2F%2Fwww.nowcoder.com%2Fexam%2Fcompany%3FcurrentTab%3Drecommand%26jobId%3D100%26keyword%3D%E5%B0%8F%E7%B1%B3%26selectStatus%3D0&testclass=%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91
    来源：牛客网*/


    //如果利用额外的数组空间那就和普通的合并数组没有什么区别。
}
