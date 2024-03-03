package model;

import java.util.List;
import java.util.Random;

public enum ToyColor {
    RED("red"),GREEN("green"),BLUE("blue"),YELLOW("yellow"),BLACK("black"),
    WHITE("white"),ORANGE("orange");

    ToyColor(String title){
        this.title = title;
    }
    private String title;
    public String getTitle(){
        return this.title;
    }
    private static final List<ToyColor> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();
    public static ToyColor randomColor()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    @Override
    public String toString() {
        return this.title;
    }
}
