package LeetCode数据结构与算法基础.模拟;


//质因数分解，有些数可以只分解为2，3，5这三个质因数中的数，打印输出前n个这样的数。
public class 分解质因数 {

    public static void main(String[] args) {
        int n = 10; // 找出前n个只能被2、3、5整除的数
        printNumbersWith235Factors(n);
    }

    public static void printNumbersWith235Factors(int n) {
        int k = 0;
        int record = 2; //从2开始
        while (k < n){
            int start = record;
            for(int i = start;;i++){
                if(is235Number(i)){
                    System.out.println(i);
                    k++;
                    record = i + 1;//记录下一个起始数据
                    break;
                }
            }
        }
    }

    //判断num能否分解成除了1以外，只由2，3，5构成
    public static boolean is235Number(int num) {
        while (num % 2 == 0) {
            num /= 2;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 5 == 0) {
            num /= 5;
        }
        //如果只由2，3，5组成，那么最后num一定等于1。
        return num == 1;
    }
}
