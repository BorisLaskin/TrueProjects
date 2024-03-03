package model;

public enum ToyType {

    EXTRASMALL("ExtrSmall"),
    SMALL("SMALL"),
    MIDIUM("Midium"),
    GIGANTIC("Gigantic");

    ToyType(String title){
        this.title = title;
    }
    private final String title;
    public String getTitle(){
        return this.title;
    }

}
