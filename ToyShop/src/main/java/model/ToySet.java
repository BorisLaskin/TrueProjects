package model;

import MyExceptions.ToyExistException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToySet {
    private final Map<Toy, Integer> toyMap = new HashMap<>();
    private int idCounter = 1000000;

    private void generateNewID() {
        this.idCounter++;
    }

    public void addNewToy(String name, ToyType type) {
        Toy temp = new Toy(1, name, type);
        if (this.toyMap.containsKey(temp)) {
            throw new ToyExistException(this.toyMap.get(temp));
        } else {
            generateNewID();
            this.toyMap.putIfAbsent(new Toy(this.idCounter, name, type), this.idCounter);
        }
    }
    public void addNewToy(Toy toy){
        this.addNewToy(toy.getName(), toy.getType());
    }

    public Map<Toy, Integer> getMap() {
        return this.toyMap;
    }
    public List<Toy> getToyList() {
        return new ArrayList<Toy>(this.toyMap.keySet());
    }
    public Toy getToyById(int id){
        for (Toy item: this.toyMap.keySet()) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }
    public List<Toy> getToyListByType(ToyType type){
        List<Toy> list = new ArrayList<>();
        for (Toy item: this.toyMap.keySet()) {
            if (item.getType().equals(type))
                list.add(item);
        }
        return list;
    }
}
