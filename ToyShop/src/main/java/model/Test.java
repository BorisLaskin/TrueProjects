package model;

public class Test {
    public static void main(String[] args) {
        ToySet newSet = new ToySet();
        newSet.addNewToy("Ula",ToyType.MIDIUM);
        newSet.addNewToy("Misha",ToyType.EXTRASMALL);
        newSet.getList().forEach((k, v) -> System.out.println(Integer.toString(v)+" "+k.getName()));
        Toy mishka1 = new Toy(1,"Misha", ToyType.EXTRASMALL);
        Toy mishka2 = new Toy(1,"Misha", ToyType.EXTRASMALL);
        Toy mishka3 = new Toy(1,"Misha", ToyType.EXTRASMALL);
        Integer a = newSet.internalAdd(mishka1);
        System.out.println(a.toString());


        System.out.println(mishka1.toString()+'\n'+mishka2.toString()+'\n'+mishka3.toString());
    }
}
