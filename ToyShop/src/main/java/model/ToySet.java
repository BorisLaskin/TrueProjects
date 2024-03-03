package model;

import MyExceptions.ToyExistException;

import java.util.HashMap;
import java.util.Map;

public class ToySet {
    private final Map<Toy, Integer> toyMap = new HashMap<>();
    private int idCounter = 1000000;

    private void generateNewID() {
        this.idCounter++;
    }

    public void addNewToy(String name, ToyType type) {
        generateNewID();
        this.toyMap.putIfAbsent(new Toy(this.idCounter, name, type), this.idCounter);
    }

    public Integer internalAdd(Toy toy) {
        Integer id = this.toyMap.putIfAbsent(toy, toy.getId());
        if (id != null) {
            throw new ToyExistException(this.toyMap.get(toy));
        }
        else {
            return this.toyMap.get(toy);
        }
    }

    public Map<Toy, Integer> getList() {
        return toyMap;
    }
}
