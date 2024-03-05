package Controller;

import model.*;
import View.*;
import java.util.List;
import java.util.Random;

public abstract class ToyShopRobot {
    List<ToyTank> toyTanks;
    Random randomGenerator;
    abstract Toy getOneToy(ToyTank tank);
    abstract void Lottery(AbstractView view);
}
