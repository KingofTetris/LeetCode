package 设计模式.建造者模式;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */

//建造者模式和工厂模式比较类似，不过工厂模式是一整个给你
//而建造者模式则更灵活，类似于DIY，你喜欢什么就往上面加上。
//生成器模式让你能够分步骤创建复杂对象。 生成器不允许其他对象访问正在创建中的产品

/**
 *  建造者模式优点：
 *  1.你可以分步创建对象， 暂缓创建步骤或递归运行创建步骤。
 *  2.生成不同形式的产品时， 你可以复用相同的制造代码。
 *  3.单一职责原则。 你可以将复杂构造代码从产品的业务逻辑中分离出来。
 *
 *  缺点：
 * 由于该模式需要新增多个类， 因此代码整体复杂程度会有所增加。
 *
 */
public class BuilderPattern {


    //这个模式在链式编程中就经常用到，选择好需要的部分，最后进行builder
    public static void main(String[] args) {
        FoodStore foodStore=new FoodStore();
        Meal meal=foodStore.createMeal(new Breakfast());
        Meal meal2=foodStore.createMeal(new Lunch());
        System.out.println("小明早上吃的是:"+meal.getFood()+",喝的饮料是:"+meal.getDrinks());
        System.out.println("小明中午吃的是:"+meal2.getFood()+",喝的饮料是:"+meal2.getDrinks());
    }

}

//核心导演，Director：用来创建复杂对象的部分，对该部分进行完整的创建或者按照一定的规则进行创建。
class FoodStore{
    public Meal createMeal(IBuilderFood bf){
        bf.buildDrinks();
        bf.buildFood();
        return bf.createMeal();
    }
}


//一顿饭，需要有吃有喝。
class Meal{
    private String food;
    private String drinks;

    public String getFood() {
        return food;
    }
    public void setFood(String food) {
        this.food = food;
    }

    public String getDrinks() {
        return drinks;
    }
    public void setDrinks(String drinks) {
        this.drinks = drinks;
    }
}

interface IBuilderFood{
    void buildFood();
    void buildDrinks();
    Meal createMeal();
}

class Breakfast implements IBuilderFood{
    Meal meal;

    public Breakfast(){
        meal=new Meal();
    }

    @Override
    public void buildFood() {
        meal.setFood("煎饼");
    }

    @Override
    public void buildDrinks() {
        meal.setDrinks("豆浆");
    }

    @Override
    public Meal createMeal() {
        return meal;
    }
}

class Lunch implements IBuilderFood{
    Meal meal;

    public Lunch(){
        meal=new Meal();
    }

    @Override
    public void buildFood() {
        meal.setFood("盒饭");
    }

    @Override
    public void buildDrinks() {
        meal.setDrinks("果汁");
    }

    @Override
    public Meal createMeal() {
        return meal;
    }
}
