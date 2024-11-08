package 设计模式.适配器模式;

/**
 * @author by KingOfTetris
 * @date 2024/11/8
 */
public class AdapterPatten {

    //适配器模式 就是给两个本来不相容的东西之间加上一个转化器，让他们可以交流
    //比如手机是不能直接使用交流电源充电的，加上一个充电器把交流转直流就可以了。
    //充电器就相当于一个适配器，他充当了手机和电源的桥梁。

    public static void main(String[] args) {
        Speaker speaker = new Speaker();
        //传入需要适配的对象
        Adapter adapter = new Adapter(speaker);
        System.out.println(adapter.translate());
        //用户调用就只需要调用adapter，而不需要去关系speaker了。
    }
}


class Speaker{
    public String speak(){
        return "China no.1";
    }
}

interface traslantor{
    String translate();
}

class Adapter implements  traslantor{

    //适配可以使用继承，关联使用。
    //我们直接关联使用吧
    //直接传入一个关联对象
    private Speaker speaker;

    public Adapter(Speaker speaker){
        this.speaker = speaker;
    }

    @Override
    public String translate() {
        //那么适配器就可以先获取到speaker的speak方法值
        String content = speaker.speak();
        /**
         * 对返回值进行处理，转化成手语...
         *
         * 这里就是进行适配操作的地方。
         * 我们只是简单的模拟一下
         */
        content += " translate";

        //然后返回处理结果即可。
        return content;
    }
}
