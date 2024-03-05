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
    private final ToySet myToys;
    public FourTypeToyShopRobot() {
        this.toyTanks = new ArrayList<>();
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
        this.myToys = newSet;
    }

    @Override
    Toy getOneToy() {
        double value = this.randomGenerator.nextDouble();
        Toy result;
        ToyTank currentTank = this.toyTanks.get(0);
        if(currentTank.getListOfTank().size() < currentTank.getTankCapacity()/4){
            this.myToys.generateAllByCountByType(currentTank.type, currentTank.getTankCapacity()/2);
            currentTank.fullTank(this.myToys);
        }
        double currentPossability = 0;

        for (int i = 0; i < toyTanks.size(); i++) {
            currentPossability += toyTanks.get(i).getPossibility();
            if (value < currentPossability){
                currentTank = toyTanks.get(i);
                break;
            }
        }
        result = currentTank.getToyFromPriseQueue();
        if (result != null) return result;
        else return getOneToy();
    }

    @Override
    public void Lottery(AbstractView view) {
        int countToPrise = view.getCountToPrise();
        List<Toy> result = new ArrayList<>();
        for (int i = 0; i < countToPrise; i++) {
            result.add(getOneToy());
        }
        view.getResults(result);
        view.printLotteryResults();
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
