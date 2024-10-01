package 校招笔试真题.CVTE._20240906;

class MyThread extends Thread {
    public static void main(String args[]) {
        MyThread t = new MyThread();
        MyThread s = new MyThread();
        t.start();
        System.out.print("one.");
        s.start();
        System.out.print("two.");
    }

    public void run() {
        System.out.print("Thread");
    }
}
