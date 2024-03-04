package model;

import java.util.Objects;

public class Toy {

    private final int Id;
    private final String name;
    private final ToyType type;
    private final ToyColor color;

    public Toy newEqualToy() {
        return new Toy(this.getId(), this.getName(), this.getType());
    }

    public Toy(int Id, String name, ToyType type) {
        this.Id = Id;
        this.name = name;
        this.type = type;
        this.color = ToyColor.randomColor();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            /*и верните */
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Toy another = (Toy) obj;
        return (this.name.equals(another.name)) && (this.type == another.type);
    }

    @Override
    public String toString() {
        return this.name + " (" + this.color + ")";
    }



    public int getId() {
        return this.Id;
    }

    public String getName() {
        return this.name;
    }

    public ToyType getType() {
        return this.type;
    }

    public ToyColor getColor() {
        return this.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
