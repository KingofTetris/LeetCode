package 十大排序;

/**
 * @author by KingOfTetris
 * @date 2022/9/6
 * 待排序的元素需要实现 Java 的 Comparable 接口，该接口有 compareTo() 方法，可以用它来判断两个元素的大小关系。
 *
 * 使用辅助函数 less() 和 swap() 来进行比较和交换的操作，使得代码的可读性和可移植性更好。
 *
 * 排序算法的成本模型是比较和交换的次数。
 *
 * 另外2. Java 的排序算法实现
 * Java 主要排序方法为 {@link java.util.Arrays .sort()}，
 * 对于基本数据类型使用三向切分的快速排序，对于引用类型使用归并排序。
 */
public abstract class Sort<T extends Comparable<T>>{
    public abstract void sort(T[] nums);//抽象方法 子类继承后重写
    //protected方法 子类能直接使用
    protected boolean less(T v, T w) {
        //如果v比w小
        return v.compareTo(w) < 0;
    }
    //交换数组a中,i,j位置上的元素
    protected void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
