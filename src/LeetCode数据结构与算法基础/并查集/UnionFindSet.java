package LeetCode数据结构与算法基础.并查集;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/24
 */
public class UnionFindSet {
    public int[] elem;

    public UnionFindSet(int n){
        this.elem = new int[n];
        Arrays.fill(elem,-1);
    }

    //查询x的根节点，返回根节点的下标
    public int findRoot(int x){
        if(x < 0){
            throw new IndexOutOfBoundsException("下标不合法，是负数");
        }
        //一直等到数组里面的值为负数时，才找到一个根
        while(elem[x] >= 0){
            x = elem[x];
        }
        return x;
    }

    //查询x1和x2是否是同一个集合
    public boolean isSameUnionFindSet(int x1,int x2){
        int index1 = findRoot(x1);
        int index2 = findRoot(x2);
        if(index1 == index2) return true;
        return false;
    }

    //这是合并操作
    public void union(int x1,int x2){
        int index1 = findRoot(x1);
        int index2 = findRoot(x2);
        if(index1 == index2){
            return ;
        }
        elem[index1] = elem[index1] + elem[index2];
        elem[index2] = index1;
    }

    //查询集合的个数
    public int getCount(){
        int count = 0;
        for(int x : elem){
            if(x < 0) count++;
        }
        return count;
    }

/*————————————————

    版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。

    原文链接：https://blog.csdn.net/2302_79862386/article/details/139607675*/
}
