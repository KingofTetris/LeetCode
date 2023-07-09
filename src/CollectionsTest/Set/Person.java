package CollectionsTest.Set;

import java.util.Objects;

/**
 * @author by KingOfTetris
 * @date 2023/6/27
 */
public class Person {
    int age;
    String sex;
    Pet pet;

    public Person(int age, String sex, Pet pet) {
        this.age = age;
        this.sex = sex;
        this.pet = pet;
    }

    public Person(){

    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age && sex.equals(person.sex) && pet.equals(person.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, sex, pet);
    }
}
