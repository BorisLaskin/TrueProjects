package model;

public class Test {
    public static void main(String[] args) {
        ToySet newSet = new ToySet();
        newSet.addNewToy("Ula", ToyType.MIDIUM);
        newSet.addNewToy("Misha", ToyType.EXTRASMALL);
        newSet.getMap().forEach((k, v) -> System.out.println(Integer.toString(v) + " " + k.getName()));
        Toy mishka1 = new Toy(1, "Misha", ToyType.EXTRASMALL);
        newSet.addNewToy("Ball",ToyType.MIDIUM);
//        newSet.addNewToy(mishka1);
        newSet.getMap().forEach((k, v) -> System.out.println(Integer.toString(v) + " " + k.getName()));
        newSet.getToyListByType(ToyType.GIGANTIC).forEach(k-> System.out.println(k.getName()+' '+k.getId()));

    }
}
