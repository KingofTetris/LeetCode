package 校招笔试真题.携程._20230907;


//排列用回溯也可以过
public class GoodSequences {

    public static void main(String[] args) {
        int n = 10;
        int count = countGoodSequences(n);
        System.out.println("Number of good sequences with length " + n + ": " + count);
    }

    public static int countGoodSequences(int n) {
        int[] sequence = new int[n];
        boolean[] used = new boolean[n + 1]; // 记录数字是否已被使用
        return backtrack(sequence, used, 0, n);
    }

    private static int backtrack(int[] sequence, boolean[] used, int index, int n) {
        if (index == n) {
            return 1; // 找到一个满足条件的序列
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (!used[i] && (index == 0 || !isPrime(i + sequence[index - 1]))) {
                sequence[index] = i;
                used[i] = true;
                count += backtrack(sequence, used, index + 1, n);
                used[i] = false;
            }
        }
        return count;
    }

    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


}
