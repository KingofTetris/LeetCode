package LeetCode数据结构与算法基础.day2.字符串;

public class 判断IPv4地址是否合法 {

    public static void main(String[] args) {
        String ip = "192.168.1.1";
        boolean isValidIp = isValidIPv4(ip);
        System.out.println("Is the IP address valid? " + isValidIp);
    }

    // 验证IPv4地址是否合法
    public static boolean isValidIPv4(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }

        String[] parts = ip.split("\\.");
        if (parts.length != 4) {
            return false;
        }

        for (String part : parts) {
            try {
                int num = Integer.parseInt(part);
                if (num < 0 || num > 255) {
                    return false;
                }
                if (part.length() > 1 && part.startsWith("0")) {
                    return false; // 不允许有前导0
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }
}
