package LeetCode数据结构与算法基础.手撕算法.读取文件;

import java.io.*;


/**
 * 一次读取固定大小的数据块（使用 FileInputStream 和缓冲区）：
 * 适用性：适用于任何类型的文件，不限于文本文件，可以更灵活地处理不同类型的数据。
 * 灵活性：可以按照指定的数据块大小读取文件，适用于需要以固定大小块处理文件内容的情况。
 * 效率：一次读取固定大小的数据块通常比逐行读取效率更高，特别是对于大文件，减少了频繁读取文件的开销。
 */
public class 读取指定大小文件_bufferSize {

    public static void main(String[] args) {
        String fileName = "src/example.txt";
        int bufferSize = 1024;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            byte[] buffer = new byte[bufferSize];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                System.out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
