package model;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ToySet newSet = new ToySet();
        newSet.addNewToy("Ula", ToyType.MIDIUM);
        newSet.addNewToy("Mishka", ToyType.EXTRASMALL);
        newSet.addNewToy("Ball",ToyType.MIDIUM);
        newSet.addNewToy("Lego",ToyType.GIGANTIC);
        newSet.addNewToy("Car",ToyType.SMALL);
        newSet.addNewToy("Plane",ToyType.SMALL);

        Toy mishka1 = new Toy(1, "Mishka", ToyType.EXTRASMALL);

//        newSet.addNewToy(mishka1);
        newSet.getMap().forEach((k, v) -> System.out.println(k.getId() + " " + k.getName()));
        newSet.getToyListByType(ToyType.MIDIUM).forEach(k-> System.out.println(k.getName()+' '+k.getId()));
        newSet.generateAllByCount(newSet.getToyList(),10);
        var list = newSet.getMap().get(newSet.getToyById(1000005));
//        list.forEach(k -> System.out.println(k.toString()));

        ToyTank tankMID = new ToyTank(ToyType.MIDIUM,15,0.2);
        tankMID.fullTank(newSet);
        tankMID.getListOfTank().forEach(k -> System.out.println(k.toString()));
//        tankMID.mixTank();
        tankMID.getListOfTank().forEach(k -> System.out.println(k.toString()));

    }
}
