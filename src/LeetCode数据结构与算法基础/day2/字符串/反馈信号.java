package LeetCode数据结构与算法基础.day2.字符串;

public class 反馈信号 {

    public static void main(String[] args) {
        System.out.println(feedback("101010")); // 输出“101010”
        System.out.println(feedback("100011")); // 输出“100100”
        System.out.println(feedback("110011")); // 输出“1000000”
    }
    public static String feedback(String signal) {
        if (!containsConsecutiveOnes(signal)) {
            return signal;
        } else {
            return generateNonConsecutiveOnes(signal);
        }
    }
    // 检查信号中是否包含连续的1
    private static boolean containsConsecutiveOnes(String signal) {
        return signal.contains("11");
    }
    // 生成大于信号对应二进制数字且不包含连续的1的最小二进制数字
    private static String generateNonConsecutiveOnes(String signal) {
        int num = Integer.parseInt(signal, 2) + 1; // 将信号转换为整数并加1
        String binaryString = Integer.toBinaryString(num); // 获取加1后的二进制字符串
        while (binaryString.contains("11")) {
            num++;
            binaryString = Integer.toBinaryString(num);
        }
        return binaryString;
    }
}
