package LeetCode数据结构与算法基础.day1.数组;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 合并两个有序数组
 * @Time 2021/9/28  9:58
 */

/*88. 合并两个有序数组
        给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。

        请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。

        注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。



        示例 1：

        输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
        输出：[1,2,2,3,5,6]
        解释：需要合并 [1,2,3] 和 [2,5,6] 。
        合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
        示例 2：

        输入：nums1 = [1], m = 1, nums2 = [], n = 0
        输出：[1]
        解释：需要合并 [1] 和 [] 。
        合并结果是 [1] 。
        示例 3：

        输入：nums1 = [0], m = 0, nums2 = [1], n = 1
        输出：[1]
        解释：需要合并的数组是 [] 和 [1] 。
        合并结果是 [1] 。
        注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。


        提示：

        nums1.length == m + n
        nums2.length == n
        0 <= m, n <= 200
        1 <= m + n <= 200
        -109 <= nums1[i], nums2[j] <= 109


        进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？*/
public class 合并两个有序数组 {

    @Test
    public void test(){
        int[] nums1 = {0,0,0,0,1,3,5,8};
        int[] nums2 = {2,4,6,8};
      /*  int n = nums2.length;
        int m = nums1.length - n;
        merge(nums1,m,nums2,n);*/
        int[] merge = merge(nums1, nums2);
        for (int i = 0; i < merge.length; i++) {
            System.out.print(merge[i] + "\t");
        }
    }

    //最简单的方法就是把nums2放在nums1后面直接排序就行了
    //时间最快，但空间会多一点，而且没用到非递减这个性质
    //其实非递减非递增，递增递减这也的单调性数组
    //经常用二分查找和双指针
 /*   public void merge(int[] nums1, int m, int[] nums2, int n) {
        //参数顺序 要复制的数组 从哪开始复制 复制到哪个数组 从哪开始复制 复制多少位？
        System.arraycopy(nums2,0,nums1,m,n);
//        for (int i = 0; i < nums1.length; i++) {
//            System.out.println(nums1[i]);
//        }
        Arrays.sort(nums1);
    }*/

    //双指针法 设置p1 p2指向数组头部
    //实际上就是归并排序
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = 0,p2 = 0;
        int[] sorted = new int[m+n];
        int cur;

        //只要有一个指针没走完就继续
        while(p1 < m || p2 <n){
            if (p1 == m)//p1走完了，p2继续走
            {
                cur = nums2[p2];
                p2++;
            }
            else if (p2 == n){ //p2走完了，p1继续走
                cur = nums1[p1];
                p1++;
            }
            //在这里会比较大小，小的排前面。所以上面可以直接赋值给cur 不用再考虑比较大小
            // 当p1和p2都走完就退出循环了
            else if(nums1[p1] > nums2[p2])
            {cur = nums2[p2];
                p2++;}
            else{
                cur = nums1[p1];
                p1++;
            }

            //注意赋值的下标是p1 + p2 - 1
            sorted[p1 + p2 - 1] = cur;
        }
        //最后赋值给nums1，但总感觉这样还限制只由nums1还有啥意义
        for (int i = 0; i < m+n; i++) {
            nums1[i] = sorted[i];
        }
    }


    //迭代合并两个有序数组.O(m+n) 如果是递归的话还需要额外的递归栈
    public int[] merge(int[] nums1,int[] nums2){
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] res = new int[n1 + n2];
        //选择短的开始
        if (n1 < n2)
            merge(nums2,nums1);
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < n1 && j < n2){
            if (nums1[i] < nums2[j]){
                res[index++] = nums1[i];
                i++;
            }
            else if (nums1[i] > nums2[j]){
                res[index++] = nums2[j];
                j++;
            }
            else {
                res[index++] = nums1[i];
                i++;
            }
            if (i >= n1 || j >= n2){
                break;
            }
        }

        if(i == n1){
            for (int k = j; k < n2; k++) {
                res[index++] = nums2[k];
            }
        }
        if (j == n2){
            for (int k = i; k < n1; k++) {
                res[index++] = nums1[k];
            }
        }

        return res;
    }

}
