package model;

import MyExceptions.*;
import java.util.*;

public class ToySet {
    private final Map<Toy, ArrayDeque<Toy>> toyMap = new HashMap<>();
    private int idCounter = 1000000;

    private void generateNewID() {
        this.idCounter++;
    }

    public void addNewToy(String name, ToyType type) {
        Toy temp = new Toy(1, name, type);
        if (this.toyMap.containsKey(temp)) {
            for (Toy item : this.toyMap.keySet()) {
                if (item.equals(temp)) {
                    throw new ToyExistException(item.getId());
                }
            }
        } else {
            generateNewID();
            this.toyMap.putIfAbsent(new Toy(this.idCounter, name, type), new ArrayDeque<>());
        }
    }

    public void addNewToy(Toy toy) {
        this.addNewToy(toy.getName(), toy.getType());
    }

    public Map<Toy, ArrayDeque<Toy>> getMap() {
        return this.toyMap;
    }

    public List<Toy> getToyList() {
        return new ArrayList<>(this.toyMap.keySet());
    }

    public Toy getToyById(int id) {
        for (Toy item : this.toyMap.keySet()) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }

    public List<Toy> getToyListByType(ToyType type) {
        List<Toy> list = new ArrayList<>();
        for (Toy item : this.toyMap.keySet()) {
            if (item.getType().equals(type))
                list.add(item);
        }
        return list;
    }
    public void generateNewToys(Toy toy, int count) {
        var list = this.toyMap.get(toy);
        for (int i = 0; i < count; i++) {
            list.offer(toy.newEqualToy());
        }
    }
    public void generateAllByCount(List<Toy> list,int count) {
        for (Toy toy : list) {
            generateNewToys(toy, count);
        }
    }
}
