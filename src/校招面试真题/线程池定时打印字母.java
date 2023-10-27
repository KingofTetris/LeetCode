package 校招面试真题;

/**
 * @author by KingOfTetris
 * @date 2023/10/26
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class 线程池定时打印字母 {
    private static final int LETTER_PRINT_INTERVAL = 1000; // 1000 ms = 1 second
    private static final int NUMBER_OF_LETTERS = 26; // 26 letters in the alphabet

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(NUMBER_OF_LETTERS);
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            char finalLetter = letter;
            //线程的执行体中不能直接使用for循环中的变量
            //因为线程的执行并不在循环体内部，循环遍历letter无法直接使用。
            executor.schedule(() -> {
                System.out.println(finalLetter);
            }, LETTER_PRINT_INTERVAL, TimeUnit.MICROSECONDS);
        }
        executor.shutdown(); // Shutdown the executor after the tasks are finished
    }
}
