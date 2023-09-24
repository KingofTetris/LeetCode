package 校招笔试真题.完美世界;

/**
 * @author by KingOfTetris
 * @date 2023/9/23
 */
import java.util.ArrayList;
import java.util.List;

public class 字符串数组的组合 {
    public static void main(String[] args) {
        String[] A = {"a", "b"};
        String[] B = {"x", "y", "z"};
        List<String> combinations = combine(A, B);
        System.out.println("合并后的所有组合数：" + combinations.size());
        System.out.println(combinations);
    }

    public static List<String> combine(String[] A, String[] B) {
        List<String> combinations = new ArrayList<>();
        combineHelper(A, B, 0, 0, "", combinations);
        return combinations;
    }

    public static void combineHelper(String[] A, String[] B, int indexA, int indexB, String current, List<String> combinations) {
        if (indexA == A.length && indexB == B.length) {
            combinations.add(current);
            return;
        }
        if (indexA < A.length) {
            combineHelper(A, B, indexA + 1, indexB, current + A[indexA], combinations);
        }
        if (indexB < B.length) {
            combineHelper(A, B, indexA, indexB + 1, current + B[indexB], combinations);
        }
    }
}
