package LeetCode数据结构与算法基础.手撕算法.读取文件;

import java.io.*;


/**
 * 逐行读取文件（使用 BufferedReader 和 readLine() 方法）：
 * 适用性：适用于文本文件，特别是以行为单位组织内容的文件，比如文本日志文件或配置文件。
 * 方便性：方便逐行处理文件内容，特别是当您需要对文件内容进行逐行处理时。
 * 内存消耗：逐行读取可能会消耗较多的内存，特别是对于大文件，因为每次读取会将整行内容加载到内存中。
 */
public class 读取指定大小文件_readLine {
    public static void main(String[] args) {
        String fileName = "src\\example.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
