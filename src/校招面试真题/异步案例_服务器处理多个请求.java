package 校招面试真题;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 异步案例_服务器处理多个请求 {
    private ExecutorService executorService;

    public 异步案例_服务器处理多个请求() {
        // 创建一个线程池
        executorService = Executors.newFixedThreadPool(10);
    }

    public void processRequest(int clientId) {
        CompletableFuture.supplyAsync(() -> {
            // 模拟处理请求的耗时操作
            try {
                Thread.sleep(1000); // 假设耗时1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Response for Client " + clientId;
        }, executorService).thenAccept(response -> {
            // 处理请求完成后的回调
            System.out.println("Client " + clientId + " response: " + response);
        });
    }

    public static void main(String[] args) {
        异步案例_服务器处理多个请求 server = new 异步案例_服务器处理多个请求();
        // 模拟多个客户端请求
        for (int i = 1; i <= 10; i++) {
            server.processRequest(i);
        }
        // 关闭线程池
        server.executorService.shutdown();
    }
}
