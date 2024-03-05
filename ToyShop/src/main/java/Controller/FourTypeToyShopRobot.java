package Controller;

import MyExceptions.IllegalStatsException;
import View.AbstractView;
import model.Toy;
import model.ToySet;
import model.ToyTank;
import model.ToyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FourTypeToyShopRobot extends ToyShopRobot {

    public FourTypeToyShopRobot() {
        this.toyTanks.add(new ToyTank(ToyType.EXTRASMALL, 1000, 0.65f));
        this.toyTanks.add(new ToyTank(ToyType.SMALL, 200, 0.2f));
        this.toyTanks.add(new ToyTank(ToyType.MIDIUM, 50, 0.1f));
        this.toyTanks.add(new ToyTank(ToyType.MIDIUM, 15, 0.05f));
        this.randomGenerator = new Random();

        ToySet newSet = new ToySet();
        newSet.addNewToy("Ula", ToyType.MIDIUM);
        newSet.addNewToy("Mishka", ToyType.EXTRASMALL);
        newSet.addNewToy("Ball", ToyType.MIDIUM);
        newSet.addNewToy("Lego", ToyType.GIGANTIC);
        newSet.addNewToy("Car", ToyType.SMALL);
        newSet.addNewToy("Plane", ToyType.SMALL);
        newSet.generateAllByCount(newSet.getToyList(), 100);

        for (ToyTank tank : this.toyTanks) {
            tank.fullTank(newSet);
        }
    }

    @Override
    Toy getOneToy(ToyTank tank) {
        return tank.getToyFromPriseQueue();
    }

    @Override
    void Lottery(AbstractView view) {
        int countToPrise = 20;
        for (int i = 0; i < countToPrise; i++) {


        }
    }

    public List<Double> getListOfStats() {
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < this.toyTanks.size(); i++) {
            list.add(this.toyTanks.get(i).getPossibility());
        }
        return list;
    }

    public void setListOfStats(List<Double> list) {
        if (list.size() == this.toyTanks.size()) {
            double sum = 0;
            for (int i = 0; i < list.size(); i++) {
                this.toyTanks.get(i).setPossibility(list.get(i));
                sum += list.get(i);
            }
            if (Math.round(sum) != 1) throw new IllegalStatsException(sum);
        } else throw new IllegalStatsException(list.size(), this.toyTanks.size());
    }
}
