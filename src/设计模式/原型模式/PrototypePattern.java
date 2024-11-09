package 设计模式.原型模式;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */

//类似于给你一个原型机模型，你按着这个模板生成其他副本就行了。

/**
 * 这里还是用一个的简单的示例来说明。
 * 小明和小红在同一天生日，然后我们需要给他们发送邮件进行祝福，但是由于比较懒，祝福语除了名字之外都是一样的。
 * 这时我们就可以先完成祝福语的编写，然后克隆该祝福语，最后根据不同的名称进行发送。不过这里就从简了，只是简单的打印下而
 */
public class PrototypePattern {


    /**
     * 看完原型模式的创建，是不是感觉就是和Java中克隆即为类似呢?
     * 实际上它的核心也就是克隆。
     * 克隆有两种，浅克隆和深克隆，本文主要介绍的是浅克隆。
     * 浅克隆:
     *
     * 在浅克隆中，如果原型对象的成员变量是值类型，将复制一份给克隆对象；
     * 如果原型对象的成员变量是引用类型，则将引用对象的地址复制一份给克隆对象，
     * 也就是说原型对象和克隆对象的成员变量指向相同的内存地址。
     * 简单来说，在浅克隆中，当对象被复制时只复制它本身和其中包含的值类型的成员变量，而引用类型的成员对象并没有复制。
     * 实现Cloneable接口并重写Object类中的clone()方法；
     *
     *
     * 深克隆:
     * 在深克隆中，无论原型对象的成员变量是值类型还是引用类型，
     * 都将复制一份给克隆对象，深克隆将原型对象的所有引用对象也复制一份给克隆对象。
     *
     * 简单来说，在深克隆中，除了对象本身被复制外，对象所包含的所有成员变量也将复制。
     * 实现Serializable接口，通过对象的序列化和反序列化实现克隆，可以实现真正的深度克隆。
     *
     * 使用场景:
     *
     * 类初始化的时候需要消耗大量资源的时候；
     * 获取数据库连接繁琐的时候；
     * 一个对象，有很多个修改者的时候；
     * 优点:
     * 1.可以提升性能；
     *
     * 缺点:
     * 1.因为必须实现Cloneable 接口，所以用起来可能不太方便。
     * @param args
     */
    public static void main(String[] args) {
        Mail mail = new Mail();
        mail.setMsg("生日快乐!");
        Mail mail2 = (Mail) mail.clone();
        mail.setName("小明");
        mail2.setName("小红");
        System.out.println(mail);
        System.out.println(mail2);
    }
}

class Mail implements Cloneable {
    private String name;
    private String msg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    @Override
    public String toString() {
        return name + ":" + msg;
    }

}
