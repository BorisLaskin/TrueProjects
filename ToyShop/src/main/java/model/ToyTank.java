package model;

import java.util.*;

public class ToyTank {
    public ToyType type;
    private final int tankCapacity;
    private float possibility;
    private final Deque<Toy> priseQueue = new ArrayDeque<>();
    private final List<Toy> tankList = new ArrayList<>();

    public ToyTank(ToyType type, int tankCapacity, float possibility) {
        this.type = type;
        this.tankCapacity = tankCapacity;
        this.possibility = possibility;

    }

    public void fullTank(ToySet setOfToys) {
        List<Toy> list = setOfToys.getToyListByType(this.type);
        if (!list.isEmpty())
            for (Toy toy : list) {
                while (!setOfToys.getMap().get(toy).isEmpty() && tankList.size() < this.tankCapacity) {
                    this.tankList.add(setOfToys.getMap().get(toy).poll());
                }
            }
        if (!this.tankList.isEmpty()) {
            this.mixTank();
            if (this.priseQueue.size() < 5) {
                this.pullToPriseQueue();
            }
        }

    }

    private void pullToPriseQueue() {
        for (int i = 0; i < 5; i++) {
            if (!this.tankList.isEmpty()) {
                this.priseQueue.add(this.tankList.remove(0));
            }
        }
    }
    public Toy getToyFromPriseQueue() {
        if (this.priseQueue.size()<5){
            this.pullToPriseQueue();
        }
        if (this.priseQueue.isEmpty())
            return null;
        else return this.priseQueue.poll();
    }

    public void mixTank() {
        Collections.shuffle(this.tankList);
    }

    public List<Toy> getListOfTank() {
        return this.tankList;
    }

    public int getTankCapacity() {
        return this.tankCapacity;
    }

    public float getPossibility() {
        return this.possibility;
    }

    public void setPossibility(float possibility) {
        this.possibility = possibility;
    }
}
