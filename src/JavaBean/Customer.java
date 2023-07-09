package JavaBean;

import java.util.Objects;

//        1、所有属性为private
//        2、提供默认构造方法
//        3、提供getter和setter
//        4、实现serializable接口
public class Customer {

    private int cid;
    private String name;


    /**
     * 自动生成的equals和hashCode方法
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return cid == customer.cid && Objects.equals(name, customer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cid, name);
    }

    //写个空参是为了反射造出对象 因为常常对象不是new出来的
    public Customer(){
    }

    public int getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setName(String name) {
        this.name = name;
    }
}
