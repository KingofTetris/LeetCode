package LeetCode数据结构与算法基础.day2.字符串;

import java.util.Arrays;


//Leetcode: 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
//输入: ["flower","flow","flight"]
//输出: "fl"
public class 最长公共前缀 {

	// 测试
	public static void main(String[] args) {
		String[] strs = { "castomer", "car", "cat" };
		// String[] strs = { "customer", "car", null };//空串
		// String[] strs = {};//空串
		// String[] strs = null;//空串
		System.out.println(maxAllQZ(strs));// c
	}

	public static String maxAllQZ(String[] strs) {
		// 如果检查值不合法及就返回空串
		if (!checkStrs(strs)) {
			return "";
		}
		// 数组长度
		int len = strs.length;
		// 用于保存结果
		StringBuilder res = new StringBuilder();
		// 给字符串数组的元素按照升序排序(包含数字的话，数字会排在前面)
		Arrays.sort(strs);
		//字符串排序以后，就只需要比较第一个和最后一个就行了
		//最少就是根本不匹配，最大就是第一个字符串
		int m = strs[0].length();
		int n = strs[len - 1].length();
		int num = Math.min(m, n);
		for (int i = 0; i < num; i++) {
			if (strs[0].charAt(i) == strs[len - 1].charAt(i)) {
				res.append(strs[0].charAt(i));
			} else
				break;
		}
		return res.toString();
	}

	private static boolean checkStrs(String[] strs) {
		boolean flag = false;
		if (strs != null) {
			// 遍历strs检查元素值
			for (int i = 0; i < strs.length; i++) {
				if (strs[i] != null && strs[i].length() != 0) {
					flag = true;
				} else {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}


}

