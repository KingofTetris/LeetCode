package 校招面试真题;

public class OuterClass {
    private int value = 10;

    public void outerMethod() {
        System.out.println("Outer method");
    }

    public void createInnerClass() {
        // 创建静态匿名内部类的实例
        InnerClass inner = new InnerClass() {
            @Override
            public void innerMethod() {
                // 在匿名内部类中调用外部类的方法
                outerMethod();
                System.out.println("Value from outer class: " + value);
            }
        };

        // 调用匿名内部类的方法
        inner.innerMethod();
    }

    public static void main(String[] args) {
        OuterClass outer = new OuterClass();
        outer.createInnerClass();
    }

    // 定义静态匿名内部类
    abstract static class InnerClass {
        public abstract void innerMethod();
    }
}
