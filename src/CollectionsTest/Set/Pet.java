package CollectionsTest.Set;

import java.util.Objects;

/**
 * @author by KingOfTetris
 * @date 2023/6/27
 */
public class Pet {
    String name;

    public Pet(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return name.equals(pet.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
